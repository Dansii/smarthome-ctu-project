package cz.cvut.fel.omo.SmartHome.devices.weather.wind.outsideBlinds.states;

import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDeviceState;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;
import cz.cvut.fel.omo.SmartHome.util.RandomUtil;

public class OutsideBlindsDownState extends AbstractWeatherDeviceState {

    @Override
    public void consume(AbstractDevice abstractDevice) {
        abstractDevice.consumes(1);
    }

    @Override
    public void processChanges(AbstractWeatherDevice abstractWeatherDevice) {
        if(RandomUtil.getRandomIntInRange(0, 1000) == 2){
            abstractWeatherDevice.setTimeUntilRepair(RandomUtil.getRandomIntInRange(1,10));
            abstractWeatherDevice.setState(new OutsideBlindsBrokenState());
            EventsKeeper.getInstance().addWeatherEvent( " became broken ",abstractWeatherDevice);

            return;
        }
        double windSpeed = abstractWeatherDevice.getWeatherDeviceSetUp().getWindSpeedMax();
        if(abstractWeatherDevice.getFloor().getWindSpeed() < windSpeed){
            EventsKeeper.getInstance().addWeatherEvent( " set up",abstractWeatherDevice);
            abstractWeatherDevice.setState(new OutsideBlindsUpState());
        }
    }
}
