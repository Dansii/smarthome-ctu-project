package cz.cvut.fel.omo.SmartHome.devices.weather.temperature.splitSystem;

import cz.cvut.fel.omo.SmartHome.devices.Consumption;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDeviceState;
import cz.cvut.fel.omo.SmartHome.devices.weather.WeatherDeviceSetUp;
import cz.cvut.fel.omo.SmartHome.devices.weather.temperature.splitSystem.states.SplitSystemBrokenState;
import cz.cvut.fel.omo.SmartHome.devices.weather.temperature.splitSystem.states.SplitSystemOffState;
import cz.cvut.fel.omo.SmartHome.devices.weather.temperature.splitSystem.states.SplitSystemOnState;
import cz.cvut.fel.omo.SmartHome.home.Floor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SplitSystem extends AbstractWeatherDevice {

    public SplitSystem(Floor floor, String deviceName, Consumption consumption, WeatherDeviceSetUp weatherDeviceSetUp) {
        super(floor, deviceName, consumption, weatherDeviceSetUp);
        this.state = new SplitSystemOnState();
    }

    @Override
    public boolean isEntertaining() {
        return false;
    }

    @Override
    public void turnOff() {
        setState(new SplitSystemOffState());
    }

    @Override
    public void turnOn() {
        if(isBroken()){
            setState(new SplitSystemBrokenState());
        } else{
            setState(new SplitSystemOnState());
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
