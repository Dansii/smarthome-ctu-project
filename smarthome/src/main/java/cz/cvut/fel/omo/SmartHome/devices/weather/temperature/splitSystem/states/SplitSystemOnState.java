package cz.cvut.fel.omo.SmartHome.devices.weather.temperature.splitSystem.states;

import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDeviceState;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;
import cz.cvut.fel.omo.SmartHome.util.RandomUtil;

public class SplitSystemOnState extends AbstractWeatherDeviceState {

    @Override
    public void consume(AbstractDevice abstractDevice) {
        abstractDevice.consumes(1);
    }

    @Override
    public void processChanges(AbstractWeatherDevice abstractWeatherDevice) {
        if(RandomUtil.getRandomIntInRange(0, 1000) == 2){
            abstractWeatherDevice.setTimeUntilRepair(RandomUtil.getRandomIntInRange(1,10));
            abstractWeatherDevice.setState(new SplitSystemBrokenState());
            EventsKeeper.getInstance().addWeatherEvent(" became broken", abstractWeatherDevice);
            return;
        }
        double floorTemp = abstractWeatherDevice.getFloor().getFloorTemp();
        double min = abstractWeatherDevice.getWeatherDeviceSetUp().getMaxTempInHome();
        double max = abstractWeatherDevice.getWeatherDeviceSetUp().getMinTempInHome();

        if(floorTemp > max){
            EventsKeeper.getInstance().addWeatherEvent(" makes temperature decrease",abstractWeatherDevice);
            if(floorTemp - max > 5){
                abstractWeatherDevice.getFloor().addTemp(-5);
            } else{
                abstractWeatherDevice.getFloor().addTemp(max - floorTemp);
            }
        } else if(floorTemp < min){
            EventsKeeper.getInstance().addWeatherEvent(" makes temperature increase",abstractWeatherDevice);
            if(min - floorTemp > 5){
                abstractWeatherDevice.getFloor().addTemp(5);
            } else{
                abstractWeatherDevice.getFloor().addTemp(min - floorTemp);
            }
        }
    }

}
