package cz.cvut.fel.omo.SmartHome.home;

import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.alive.Role;
import cz.cvut.fel.omo.SmartHome.devices.TurnableDevicesInterface;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.event.WeatherEvent;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Floor {
    private List<AbstractWeatherDevice> weatherDevices;
    private List<AbstractActionDevice> actionDevices;

    private double floorTemp;
    private double sunshine;
    private double windSpeed;

    public Floor(double floorTemp, double sunshine, double windSpeed) {
        this.actionDevices = new ArrayList<>();
        this.weatherDevices = new ArrayList<>();
        this.floorTemp = floorTemp;
        this.sunshine = sunshine;
        this.windSpeed = windSpeed;
    }

    public void reactToWordChanges(WeatherEvent weatherEvent) {
        floorTemp += weatherEvent.getTemperature() * 0.8;
        sunshine += weatherEvent.getSun();
        windSpeed += weatherEvent.getWind();
    }

    public Creature findFreeAdultsOnCurrentFloor() {
        return House.getInstance().getCreatures().stream()
                .filter(t -> t.getFloor() == this && t.isFree() && t.getRole() == Role.ADULT)
                .findFirst().orElse(null);
    }

    public List<TurnableDevicesInterface> getTurnableDevices() {
        List<TurnableDevicesInterface> turnableDevices = new ArrayList<>();

        actionDevices.stream()
                .filter(this::isTurnable)
                .forEach(s -> turnableDevices.add((TurnableDevicesInterface) s));

        turnableDevices.addAll(weatherDevices);

        return turnableDevices;
    }

    private boolean isTurnable(AbstractActionDevice t) {
        return TurnableDevicesInterface.class.isAssignableFrom(t.getClass());
    }

    public void addTemp(double temp) {
        floorTemp += temp;
    }

}
