package cz.cvut.fel.omo.SmartHome.devices.weather.wind.outsideBlinds.states;

import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDeviceState;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;

public class OutsideBlindsOffState extends AbstractWeatherDeviceState {

    @Override
    public void consume(AbstractDevice abstractDevice) {
        abstractDevice.consumes(0.01);
    }

    @Override
    public void processChanges(AbstractWeatherDevice abstractWeatherDevice) {
        EventsKeeper.getInstance().addWeatherEvent( " is off, changes are not processed", abstractWeatherDevice);
    }
}
