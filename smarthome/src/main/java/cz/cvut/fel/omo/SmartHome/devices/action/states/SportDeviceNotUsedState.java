package cz.cvut.fel.omo.SmartHome.devices.action.states;

import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.home.House;

/**
 * The class implements the sport device logic in the not used state.
 */
public class SportDeviceNotUsedState extends AbstractActionDeviceState {

    @Override
    public void action(AbstractActionDevice deviceUsable, Creature person) {
        if (!deviceUsable.isBroken()) {
            person.addTimeUntilFree(deviceUsable.getDurationOfUsing());
            person.setCurrentDevice(deviceUsable);
            deviceUsable.setState(new SportDeviceUsedState());
            deviceUsable.action(person);
        } else {
            person.release();
            deviceUsable.setState(new SportDeviceBrokenState());
            House.getInstance().addBrokenDevice(deviceUsable);
        }
    }

    @Override
    public boolean isFree() {
        return true;
    }

    @Override
    public void consume(AbstractDevice device) {

    }
}
