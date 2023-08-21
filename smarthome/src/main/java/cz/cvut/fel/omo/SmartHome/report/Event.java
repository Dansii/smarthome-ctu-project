package cz.cvut.fel.omo.SmartHome.report;
import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;


/**
 * Abstract class, the base for all events in reports
 *
 */
public abstract class Event {
    protected final String from;
    protected final AbstractDevice device;

    public Event(String from, AbstractDevice device) {
        this.from = from;
        this.device = device;
    }

    public abstract String toString();

    public String getFrom() {
        return from;
    }

    public AbstractDevice getDevice() {
        return device;
    }
}
