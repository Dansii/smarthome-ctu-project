package cz.cvut.fel.omo.SmartHome.devices.weather.event;

import lombok.Getter;
import lombok.Setter;

/**
 * Class that represent changes, from world.
 * Sent from world to each floor for reacting to changes.
 */
@Getter
@Setter
public class WeatherEvent {

    private double sun;

    private double wind;

    private double temperature;

    public WeatherEvent(double sun, double wind, double temperature) {
        this.sun = sun;
        this.wind = wind;
        this.temperature = temperature;
    }

}
