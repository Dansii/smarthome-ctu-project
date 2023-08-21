package cz.cvut.fel.omo.SmartHome.report;

import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that keeps all events and collect them
 *
 */
public class EventsKeeper {

    private List<Event> events;
    private static volatile EventsKeeper instance = null;
    public EventsKeeper() {
        events = new ArrayList<>();
    }

    public static EventsKeeper getInstance() {
        if (instance == null) {
            instance = new EventsKeeper();
        }
        return instance;
    }

    /**
     * Creating a new interaction event for reports and adds it to the list
     * @param from initiator of interaction
     * @param device object of interaction
     */
    public void addInteractionEvent(String from, AbstractDevice device){
        events.add(new InteractionEvent(from, device));
    }

    /**
     * Creating a new repair event for reports and adds it to the list
     * @param from initiator of interaction
     * @param device object of interaction
     */
    public void addRepairEvent(String from, AbstractDevice device){
        events.add(new RepairEvent(from, device));
    }

    /**
     * Creating a new weather event for reports and adds it to the list
     * @param from initiator of interaction
     * @param device object of interaction
     */
    public void addWeatherEvent(String from, AbstractDevice device){
        events.add(new WeatherEvent(from, device));
    }

    /**
     * Creating a new strategy event for reports and adds it to the list
     * @param from initiator of interaction
     * @param device object of interaction
     */
    public void addStrategyEvent(String from, AbstractDevice device){
        events.add(new EventStrategy(from, device));
    }


    public List<Event> getEvents() {
        return events;
    }
}
