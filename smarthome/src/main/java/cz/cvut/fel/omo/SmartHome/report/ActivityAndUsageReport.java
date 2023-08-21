package cz.cvut.fel.omo.SmartHome.report;

import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.home.House;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * Class for Activity and usage report
 *
 */
public class ActivityAndUsageReport extends AbstractReport {

    private final House house = House.getInstance();
    private List<Event> events;
    public ActivityAndUsageReport() {
        events = new ArrayList<>();
    }


    /**
     * Generates a string that contains information about house configuration
     * @return string with report
     */
    public String report() {
        StringBuilder sb = new StringBuilder();

        if (events.isEmpty()) {
            sb.append("There is no events");
        } else {
            for (Creature creature : house.getCreatures()) {
                List<Event> creatureEvents = events.stream()
                        .filter(t -> t.getFrom().equals(creature.getName()))
                        .collect(Collectors.toList());
                Map<AbstractDevice, Long> deviceCountList = creatureEvents.stream()
                        .collect(Collectors.groupingBy(Event::getDevice, Collectors.counting()));
                deviceCountList.forEach((device, count) ->
                        sb.append(creature.getName())
                                .append(" interacted with ")
                                .append(device.getDeviceName())
                                .append(" for ")
                                .append(count)
                                .append(" times \n"));
            }
        }

        return sb.toString();
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public void accept(ReportGenerator generator) {
        generator.generateActivityAndUsageReport(this);
    }

}
