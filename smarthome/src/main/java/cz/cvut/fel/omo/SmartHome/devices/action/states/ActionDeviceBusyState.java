package cz.cvut.fel.omo.SmartHome.devices.action.states;

import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.home.House;

import java.util.List;

/**
 * The class implements the action device logic in the busy state.
 */
public class ActionDeviceBusyState extends AbstractActionDeviceState{

    @Override
    public void action(AbstractActionDevice deviceUsable, Creature person) {
        if (!deviceUsable.isUsageLimit() && !person.isFree()) {
            deviceUsable.setCondition(deviceUsable.getCondition() - 5);
            person.decreasePersonTime();
            deviceUsable.usage();
            if (deviceUsable.isBroken()) {
                person.release();
                deviceUsable.setState(new ActionDeviceBrokenState());
                House.getInstance().addBrokenDevice(deviceUsable);
                return;
            }
            if (deviceUsable.isUsageLimit()) {
                person.release();
                deviceUsable.updateDuration();
                deviceUsable.setState(new ActionDeviceOnState());
            }
        } else {
            person.release();
            deviceUsable.updateDuration();
            deviceUsable.setState(new ActionDeviceOnState());
        }
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public void consume(AbstractDevice device) {
        device.consumes(1);
    }

}
