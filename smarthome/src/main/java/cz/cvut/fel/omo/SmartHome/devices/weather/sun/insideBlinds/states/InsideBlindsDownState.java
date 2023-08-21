package cz.cvut.fel.omo.SmartHome.devices.weather.sun.insideBlinds.states;

import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDeviceState;
import cz.cvut.fel.omo.SmartHome.devices.weather.temperature.splitSystem.states.SplitSystemBrokenState;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;
import cz.cvut.fel.omo.SmartHome.util.RandomUtil;

public class InsideBlindsDownState extends AbstractWeatherDeviceState {

    @Override
    public void consume(AbstractDevice abstractDevice) {
        abstractDevice.consumes(1);
    }

    @Override
    public void processChanges(AbstractWeatherDevice abstractWeatherDevice) {
        if(RandomUtil.getRandomIntInRange(0, 1000) == 2){
            abstractWeatherDevice.setTimeUntilRepair(RandomUtil.getRandomIntInRange(1,10));
            abstractWeatherDevice.setState(new InsideBlindsBrokenState());
            EventsKeeper.getInstance().addRepairEvent("Tech support worker repaired", abstractWeatherDevice);
            return;
        }
        double sunshine = abstractWeatherDevice.getWeatherDeviceSetUp().getSunshineMax();
        if(abstractWeatherDevice.getFloor().getSunshine() < sunshine){
            EventsKeeper.getInstance().addWeatherEvent( " set up", abstractWeatherDevice);
            abstractWeatherDevice.setState(new InsideBlindsUpState());
        }
    }
}
