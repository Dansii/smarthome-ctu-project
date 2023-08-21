package cz.cvut.fel.omo.SmartHome.devices.weather.wind.outsideBlinds;

import cz.cvut.fel.omo.SmartHome.devices.Consumption;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDeviceState;
import cz.cvut.fel.omo.SmartHome.devices.weather.WeatherDeviceSetUp;
import cz.cvut.fel.omo.SmartHome.devices.weather.wind.outsideBlinds.states.OutsideBlindsBrokenState;
import cz.cvut.fel.omo.SmartHome.devices.weather.wind.outsideBlinds.states.OutsideBlindsOffState;
import cz.cvut.fel.omo.SmartHome.devices.weather.wind.outsideBlinds.states.OutsideBlindsUpState;
import cz.cvut.fel.omo.SmartHome.home.Floor;

public class OutsideBlinds extends AbstractWeatherDevice {

    public OutsideBlinds(Floor floor, String deviceName, Consumption consumption, WeatherDeviceSetUp weatherDeviceSetUp) {
        super(floor, deviceName, consumption, weatherDeviceSetUp);
        this.state = new OutsideBlindsUpState();
    }

    @Override
    public boolean isEntertaining() {
        return false;
    }

    @Override
    public void turnOff() {
        setState(new OutsideBlindsOffState());
    }

    @Override
    public void turnOn() {
        if(isBroken()){
            setState(new OutsideBlindsBrokenState());
        } else{
            setState(new OutsideBlindsUpState());
        }
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
