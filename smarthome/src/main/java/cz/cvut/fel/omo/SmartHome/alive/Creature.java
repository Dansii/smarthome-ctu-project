package cz.cvut.fel.omo.SmartHome.alive;

import cz.cvut.fel.omo.SmartHome.devices.ClassificationEnum;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.home.Floor;
import cz.cvut.fel.omo.SmartHome.home.House;
import cz.cvut.fel.omo.SmartHome.report.EventReport;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;
import cz.cvut.fel.omo.SmartHome.util.RandomUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.Assert;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class Creature {

    protected ClassificationEnum wish;
    protected Floor floor;
    protected Role role;
    private AbstractActionDevice currentDevice;
    private int timeUntilFree = 0;
    private double satiety = 50;
    private String name = NameGenerator.getRandomName();

    /**
     * Creates person with role on random floor.
     * @param role  string role of person
     */
    public Creature(Role role) {
        this.role = role;
        changeFloorRandom();
    }

    /**
     * The function adjusts the desire of a person according to his satiety.
     * If the child is hungry, an adult feeds him.
     */
    public void doSomething() {
        switch (role) {
            case ADULT:
                if (satiety < 50) {
                    wish = ClassificationEnum.EATING_PERSON;
                    break;
                }
            case CHILD:
                if (satiety < 50) {
                    AbstractActionDevice device;
                    for (Floor floor: House.getInstance().getFloors()) {
                        device = floor.getActionDevices().stream()
                                .filter(t -> t.getDeviceClassification() == ClassificationEnum.EATING_PERSON && t.isFree())
                                .findFirst()
                                .orElse(null);
                        if (device != null) {
                            EventsKeeper.getInstance().addInteractionEvent("Child " + name + " is getting help with eating and ",device);
                            Creature adult = getHelp(device);
                            if (adult != null) {
                                eat();
                                adult.decreaseSatiety(20);
                            }
                            break;
                        }
                    }
                    break;
                }
                break;
            case ANIMAL:
                if (satiety < 50) {
                    wish = ClassificationEnum.EATING_PET;
                } else {
                    wish = ClassificationEnum.ACTION_PET;
                }
                doAction();
                return;
        }
        double i = RandomUtil.getRandomDoubleInRange(0, 10);
        wish = (i < 5) ? ClassificationEnum.ACTION : ClassificationEnum.SPORT;
        doAction();
    }

    /**
     * The function looks for what kind of activity a person can do on the floor on which he is located.
     * If there are no free activities on the floor, it changes the floor and searches again.
     */
    private void doAction() {
        List<Floor> floors = House.getInstance().getFloors();
        for (int i = 0; i < floors.size(); i++) {
            if (!action()) {
                changeFloor(i);
            } else {
                return;
            }
        }
    }

    /**
     * Changes the floor the person is on.
     * @param i floor number
     */
    public void changeFloor(int i) {
        House instance = House.getInstance();
        List<Floor> floors = instance.getFloors();
        this.floor = floors.get(i);
    }

    /**
     * Randomly changes the floor a person is on.
     */
    public void changeFloorRandom() {
        House house = House.getInstance();
        List<Floor> floors = house.getFloors();
        this.floor = floors.get(RandomUtil.getRandomIntInRange(0, floors.size()));

        String w = "";
        if(wish != null) w = " for " + wish.toString();
        EventsKeeper.getInstance().addStrategyEvent(name + " changed floor" + w, null);
    }

    /**
     * Continues the action of a person on a current device
     */
    public void continueAction() {
        Assert.notNull(currentDevice, "Object is null");
        currentDevice.action(this);
    }

    /**
     * Checks if a person is currently free
     * @return true if person is free
     *         false if person is busy
     */
    public boolean isFree() {
        return timeUntilFree <= 0 && currentDevice == null;
    }

    /**
     * Adds the number of iterations after which a person will be released.
     * @param add number of iterations
     */
    public void addTimeUntilFree(int add) {
        this.timeUntilFree += add;
    }

    /**
     * Decreases the number of iterations until the person is released.
     */
    public void decreasePersonTime() {
        this.timeUntilFree--;
    }

    /**
     * Finds an adult to help the child fix the device or eat.
     * @param device device with which the child needs help.
     * @return
     */
    public Creature getHelp(AbstractActionDevice device) {
        Creature adult = floor.findFreeAdultsOnCurrentFloor();
        if (adult != null) {
            device.action(adult);
            return adult;
        } else {
            for (Floor floor : House.getInstance().getFloors()) {
                adult = floor.findFreeAdultsOnCurrentFloor();
                if (adult != null) {
                    adult.setFloor(floor);
                    device.action(adult);
                    EventsKeeper.getInstance().addInteractionEvent(name + " is getting help and ",device);
                    return adult;
                }
            }
        }
        return null;
    }

    /**
     * Frees the person from activity.
     */
    public void release() {
        currentDevice = null;
        timeUntilFree = 0;
    }

    /**
     * Reduces satiety.
     * @param difference number of satiety units
     */
    public void decreaseSatiety(int difference) {
        this.satiety -= difference;
    }

    /**
     * Increases a person's satiety.
     */
    public void eat() {
        this.satiety += 20;
        EventsKeeper.getInstance().addStrategyEvent(name + "'s satiety increased ", null);

    }

    /**
     * The function assigns a device type to a person depending on his desire.
     * @return true if there are suitable devices in the house.
     *         false if there are no suitable devices in the house.
     */
    protected boolean action() {
        List<AbstractActionDevice> usableDevices = this.floor.getActionDevices();
        Collections.shuffle(usableDevices);
        switch (role) {
            case ADULT:
            case ANIMAL:
                List<AbstractActionDevice> adultsDevices = usableDevices.stream()
                        .filter(t -> t.isFree() && t.isRightDevice(this)).collect(Collectors.toList());
                if (!adultsDevices.isEmpty()) {
                    adultsDevices.get(RandomUtil.getRandomIntInRange(0, adultsDevices.size())).action(this);
                    return true;
                }
                return false;
            case CHILD:
                List<AbstractActionDevice> entertainingDevice = usableDevices.stream()
                        .filter(t -> t.isFree() && t.isRightDevice(this) && t.isEntertaining()).collect(Collectors.toList());
                if (!entertainingDevice.isEmpty()) {
                    entertainingDevice.get(RandomUtil.getRandomIntInRange(0, entertainingDevice.size())).action(this);
                    return true;
                }
                return false;
            default:
                return false;
        }
    }

    public void setCurrentDevice(AbstractActionDevice abstractActionDevice){
        this.currentDevice = abstractActionDevice;
        EventsKeeper.getInstance().addInteractionEvent(name, abstractActionDevice);
    }

}
