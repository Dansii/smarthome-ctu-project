package cz.cvut.fel.omo.SmartHome.devices.action.states;

import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;

/**
 * The class implements the action device logic in the off state.
 */
public class ActionDeviceOffState extends AbstractActionDeviceState {

    @Override
    public void action(AbstractActionDevice deviceUsable, Creature person) {
        deviceUsable.setState(new ActionDeviceOnState());
        deviceUsable.action(person);
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public void consume(AbstractDevice device) {
        device.consumes(0.05);
    }

}
