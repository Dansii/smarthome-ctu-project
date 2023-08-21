package cz.cvut.fel.omo.SmartHome.devices.action;

import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.ClassificationEnum;
import cz.cvut.fel.omo.SmartHome.devices.Consumption;
import cz.cvut.fel.omo.SmartHome.devices.action.states.AbstractActionDeviceState;
import cz.cvut.fel.omo.SmartHome.home.Floor;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;
import cz.cvut.fel.omo.SmartHome.util.RandomUtil;
import lombok.Getter;

/**
 * Abstract class for action devices.
 */
public abstract class AbstractActionDevice extends AbstractDevice {

    @Getter
    protected ClassificationEnum deviceClassification;
    protected AbstractActionDeviceState state;

    @Getter
    private int durationOfUsing;

    public AbstractActionDevice(Floor floor, String deviceName, int durationOfUsing, Consumption consumption) {
        super(floor, deviceName, consumption);
        this.durationOfUsing = durationOfUsing;
    }

    /**
     * The method implemented by the state pattern.
     * Depending on the state of the device, a different interaction of the device with a person is performed.
     * @param creature A person who interacts with device.
     */
    public void action(Creature creature){
        switch (deviceClassification) {
            case SPORT:
                creature.decreaseSatiety(7);
                break;
            case ACTION:
                creature.decreaseSatiety(4);
                break;
            case EATING_PET:
            case EATING_PERSON:
                creature.eat();
                break;
            case ACTION_PET:
                creature.decreaseSatiety(5);
                break;
        }
        this.state.action(this, creature);
    }

    @Override
    public void consume(){
        state.consume(this);
    }

    /**
     * The method determines if the device is not occupied by any person.
     * @return
     */
    public boolean isFree(){
        return state.isFree();
    }

    /**
     * Checks if a person's desire matches the device's classification.
     * @param creature person
     * @return true if it matches
     * @return false if it doesn't match
     */
    public boolean isRightDevice(Creature creature){
        return this.deviceClassification.equals(creature.getWish());
    }

    /**
     * Change device states.
     * @param state
     */
    public void setState(AbstractActionDeviceState state) {
        this.state = state;
    }


    /**
     * The method reduces the period of use of this device by a specific person.
     */
    public void usage() {
        durationOfUsing--;
    }

    /**
     * The method determines whether the use of this device by a specific person has expired.
     * @return
     */
    public boolean isUsageLimit() {
        return durationOfUsing <= 0;
    }

    /**
     * Method that changes durationOfUsing.
     * Help to simulate different times of using for different persons.
     */
    public void updateDuration() {
        durationOfUsing = RandomUtil.getRandomIntInRange(3, 7);
    }

}
