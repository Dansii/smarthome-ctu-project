package cz.cvut.fel.omo.SmartHome.devices.action.states;

import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.alive.Role;
import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.home.House;
import cz.cvut.fel.omo.SmartHome.util.RandomUtil;

/**
 * The class implements the action device logic in the broken state.
 */
public class ActionDeviceBrokenState extends AbstractActionDeviceState {

    @Override
    public void action(AbstractActionDevice deviceUsable, Creature person) {
        if (person.getRole() == Role.CHILD) {
            person.getHelp(deviceUsable);
            person.release();
            return;
        }
        if (person.isFree()) {
            person.addTimeUntilFree(RandomUtil.getRandomIntInRange(3, 5));
            person.setCurrentDevice(deviceUsable);
        }
        deviceUsable.getInstruction();
        deviceUsable.repairDevice();
        person.decreasePersonTime();
        if (!deviceUsable.isBroken()) {
            person.release();
            deviceUsable.setState(new ActionDeviceOnState());
            House.getInstance().removeBrokenDevice(deviceUsable);
        }
    }

    @Override
    public boolean isFree() {
        return false;
    }

    @Override
    public void consume(AbstractDevice device) {
        device.consumes(0.75);
    }

}
