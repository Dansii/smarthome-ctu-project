package cz.cvut.fel.omo.SmartHome.devices.weather.wind.outsideBlinds.states;

import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDeviceState;
import cz.cvut.fel.omo.SmartHome.devices.weather.temperature.splitSystem.states.SplitSystemOnState;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;

public class OutsideBlindsBrokenState extends AbstractWeatherDeviceState {
    @Override
    public void consume(AbstractDevice device) {
        device.consumes(0.75);
    }

    @Override
    public void processChanges(AbstractWeatherDevice abstractWeatherDevice) {
        abstractWeatherDevice.setTimeUntilRepair(abstractWeatherDevice.getTimeUntilRepair() - 1);
        if(abstractWeatherDevice.getTimeUntilRepair() == 0){
            EventsKeeper.getInstance().addRepairEvent("Tech support worker repaired", abstractWeatherDevice);
            abstractWeatherDevice.setState(new OutsideBlindsUpState());
        }
    }
}
