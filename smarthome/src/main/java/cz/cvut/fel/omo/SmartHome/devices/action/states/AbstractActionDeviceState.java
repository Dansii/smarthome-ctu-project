package cz.cvut.fel.omo.SmartHome.devices.action.states;

import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.devices.AbstractState;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;

/**
 * Abstract class for implementing the state pattern
 */
public abstract class AbstractActionDeviceState extends AbstractState {

    /**
     * The method implemented by the state pattern.
     * Depending on the state of the device, a different interaction of the device with a person is performed.
     * @param deviceUsable Device with which a person interacts.
     * @param person A person who interacts with device.
     */
    public abstract void action(AbstractActionDevice deviceUsable, Creature person);

    /**
     * The method determines if the device is not occupied by any person.
     * @return
     */
    public abstract boolean isFree();
}
