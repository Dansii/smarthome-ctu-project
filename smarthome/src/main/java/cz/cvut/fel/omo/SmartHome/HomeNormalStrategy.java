package cz.cvut.fel.omo.SmartHome;

import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.alive.Role;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.home.House;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class HomeNormalStrategy implements HomeStrategy {

    @Override
    public void mainCycle(List<Creature> creatures) {
        checkBrokenDevices(creatures);

        calculateConsumption();
        creatures.stream().filter(Creature::isFree).forEach(Creature::changeFloorRandom);
        creatures.stream().filter(t -> !t.isFree()).forEach(Creature::continueAction);

        Collections.shuffle(creatures);
        creatures.stream().filter(Creature::isFree).forEach(Creature::doSomething);
    }

    private void calculateConsumption(){
        House.getInstance().getFloors()
                .forEach(t -> t.getWeatherDevices()
                        .forEach(AbstractWeatherDevice::consume));
        House.getInstance().getFloors()
                .forEach(t -> t.getActionDevices()
                        .forEach(AbstractActionDevice::consume));
    }

    private void checkBrokenDevices(List<Creature> creatures) {

        List<AbstractActionDevice> brokenDevices = House.getInstance().getBrokenDevices();
        if (!brokenDevices.isEmpty()) {
            for (AbstractActionDevice brokenDevice : brokenDevices) {
                if (creatures.stream().noneMatch(Creature::isFree)) {
                    break;
                } else {
                    Creature creature = creatures.stream()
                            .filter(t -> t.isFree() && t.getRole().equals(Role.ADULT))
                            .findFirst().orElse(null);
                    if(creature!= null){
                        creature.setCurrentDevice(brokenDevice);
                        EventsKeeper.getInstance().addRepairEvent(creature.getName(), brokenDevice);
                    }
                }
            }
        }
    }

    @Override
    public boolean isEmergency() {
        return false;
    }

}
