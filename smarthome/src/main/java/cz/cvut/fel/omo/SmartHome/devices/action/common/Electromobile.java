package cz.cvut.fel.omo.SmartHome.devices.action.common;

import cz.cvut.fel.omo.SmartHome.devices.ClassificationEnum;
import cz.cvut.fel.omo.SmartHome.devices.Consumption;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.devices.action.states.ActionDeviceOffState;
import cz.cvut.fel.omo.SmartHome.home.Floor;

public class Electromobile extends AbstractActionDevice {

    public Electromobile(Floor floor, String deviceName, int durationOfUsing, Consumption consumption) {
        super(floor, deviceName, durationOfUsing, consumption);
        this.state = new ActionDeviceOffState();
        this.deviceClassification = ClassificationEnum.ACTION;
    }

    @Override
    public boolean isEntertaining() {
        return false;
    }
}
