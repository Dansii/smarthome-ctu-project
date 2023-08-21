package cz.cvut.fel.omo.SmartHome.report;

import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;

/**
 * Class for events that are caused by weather changes
 *
 */
public class WeatherEvent extends Event{

    public WeatherEvent(String from, AbstractDevice device) {
        super(from, device);
    }

    @Override
    public String toString() {
        return device.getDeviceName() + from;
    }
}
