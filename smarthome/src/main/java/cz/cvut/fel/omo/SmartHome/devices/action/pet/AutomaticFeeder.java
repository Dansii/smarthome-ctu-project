package cz.cvut.fel.omo.SmartHome.devices.action.pet;

import cz.cvut.fel.omo.SmartHome.devices.ClassificationEnum;
import cz.cvut.fel.omo.SmartHome.devices.Consumption;
import cz.cvut.fel.omo.SmartHome.devices.TurnableDevicesInterface;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.devices.action.states.ActionDeviceBrokenState;
import cz.cvut.fel.omo.SmartHome.devices.action.states.ActionDeviceOffState;
import cz.cvut.fel.omo.SmartHome.devices.action.states.ActionDeviceOnState;
import cz.cvut.fel.omo.SmartHome.home.Floor;

public class AutomaticFeeder extends AbstractActionDevice implements TurnableDevicesInterface {

    public AutomaticFeeder(Floor floor, String deviceName, int durationOfUsing, Consumption consumption) {
        super(floor, deviceName, durationOfUsing, consumption);
        this.state = new ActionDeviceOffState();
        this.deviceClassification = ClassificationEnum.EATING_PET;
    }

    @Override
    public boolean isEntertaining() {
        return false;
    }

    @Override
    public void turnOff() {
        this.state = new ActionDeviceOffState();
    }

    @Override
    public void turnOn() {
        if (isBroken()) {
            setState(new ActionDeviceBrokenState());
        } else {
            setState(new ActionDeviceOnState());
        }
    }
}
