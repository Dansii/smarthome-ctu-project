package cz.cvut.fel.omo.SmartHome.devices;

public abstract class AbstractState {

    /**
     * Method calculating consuming of device.
     */
    public abstract void consume(AbstractDevice device);

}
