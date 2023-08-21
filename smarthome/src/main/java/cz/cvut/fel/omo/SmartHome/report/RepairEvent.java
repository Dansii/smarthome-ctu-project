package cz.cvut.fel.omo.SmartHome.report;

import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;

/**
 * Class for events that are caused by the fact of repairing some device
 *
 */
public class RepairEvent extends Event{
    public RepairEvent(String from, AbstractDevice device) {
        super(from, device);
    }

    @Override
    public String toString() {
        return from + " repaired " + device.getDeviceName();
    }
}
