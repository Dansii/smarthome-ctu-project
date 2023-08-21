package cz.cvut.fel.omo.SmartHome.devices.action.sport;

import cz.cvut.fel.omo.SmartHome.devices.ClassificationEnum;
import cz.cvut.fel.omo.SmartHome.devices.Consumption;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.devices.action.states.SportDeviceNotUsedState;
import cz.cvut.fel.omo.SmartHome.home.Floor;

public class Bicycle extends AbstractActionDevice {

    public Bicycle(Floor floor, String deviceName, int durationOfUsing, Consumption consumption) {
        super(floor, deviceName, durationOfUsing, consumption);
        this.state = new SportDeviceNotUsedState();
        this.deviceClassification = ClassificationEnum.SPORT;
    }

    @Override
    public boolean isEntertaining() {
        return true;
    }
}
