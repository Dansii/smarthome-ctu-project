package cz.cvut.fel.omo.SmartHome.devices.weather;

import cz.cvut.fel.omo.SmartHome.devices.AbstractState;

/**
 * Abstract class for implementing the state pattern
 */
public abstract class AbstractWeatherDeviceState extends AbstractState{

    /**
     * The method, depending on the temperature of the house and the state of the device,
     * implements a different logic of the device's behavior.
     * @param abstractWeatherDevice dto 
     */
    public abstract void processChanges(AbstractWeatherDevice abstractWeatherDevice);

}
