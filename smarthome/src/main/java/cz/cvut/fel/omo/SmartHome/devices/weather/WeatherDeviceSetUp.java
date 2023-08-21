package cz.cvut.fel.omo.SmartHome.devices.weather;

import lombok.Getter;

/**
 * Dto for weather newSetUp() method.
 */
@Getter
public class WeatherDeviceSetUp {

    private final double maxTempInHome;
    private final double minTempInHome;
    private final double sunshineMax;
    private final double windSpeedMax;

    public WeatherDeviceSetUp(double maxTempInHome, double minTempInHome, double sunshineMax, double windSpeedMax) {
        this.maxTempInHome = maxTempInHome;
        this.minTempInHome = minTempInHome;
        this.sunshineMax = sunshineMax;
        this.windSpeedMax = windSpeedMax;
    }

}
