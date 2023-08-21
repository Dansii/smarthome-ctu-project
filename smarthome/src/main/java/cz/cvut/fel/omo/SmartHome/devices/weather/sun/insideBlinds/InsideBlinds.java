package cz.cvut.fel.omo.SmartHome.devices.weather.sun.insideBlinds;

import cz.cvut.fel.omo.SmartHome.devices.Consumption;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDeviceState;
import cz.cvut.fel.omo.SmartHome.devices.weather.WeatherDeviceSetUp;
import cz.cvut.fel.omo.SmartHome.devices.weather.sun.insideBlinds.states.InsideBlindsBrokenState;
import cz.cvut.fel.omo.SmartHome.devices.weather.sun.insideBlinds.states.InsideBlindsOffState;
import cz.cvut.fel.omo.SmartHome.devices.weather.sun.insideBlinds.states.InsideBlindsUpState;
import cz.cvut.fel.omo.SmartHome.home.Floor;

public class InsideBlinds extends AbstractWeatherDevice {

    public InsideBlinds(Floor floor, String deviceName, Consumption consumption, WeatherDeviceSetUp weatherDeviceSetUp) {
        super(floor, deviceName, consumption, weatherDeviceSetUp);
        this.state = new InsideBlindsUpState();
    }

    @Override
    public boolean isEntertaining() {
        return false;
    }

    @Override
    public void turnOff() {
        setState(new InsideBlindsOffState());
    }

    @Override
    public void turnOn() {

        if(isBroken()){
            setState(new InsideBlindsBrokenState());
        } else{
            setState(new InsideBlindsUpState());
        }

        setState(new InsideBlindsUpState());
    }

    @Override
    public void consume() {
        this.state.consume(this);
    }

    @Override
    public void setState(AbstractWeatherDeviceState state) {
        this.state = state;
    }

}
