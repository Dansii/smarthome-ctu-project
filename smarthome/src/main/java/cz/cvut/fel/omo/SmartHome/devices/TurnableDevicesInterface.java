package cz.cvut.fel.omo.SmartHome.devices;

/**
 * Methods of this interface using for Home emergency strategy.
 * After emergency situation will be finished, every person will keep doing previous action, that was interrupted.
 */
public interface TurnableDevicesInterface {

    /**
     * Method for turning device off.
     */
    void turnOff();

    /**
     * Method for turning device on. If device was broken before turning off, then it will broken after turning on.
     */
    void turnOn();

}
