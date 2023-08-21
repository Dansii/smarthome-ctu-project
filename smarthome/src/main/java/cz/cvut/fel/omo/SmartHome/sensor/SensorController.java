package cz.cvut.fel.omo.SmartHome.sensor;

import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.event.WeatherEvent;
import cz.cvut.fel.omo.SmartHome.home.Floor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SensorController {

    private final List<Floor> floors;

    public SensorController(List<Floor> floors){
        this.floors = floors;
    }

    public void processChanges(){
        floors.forEach(t -> t.getWeatherDevices().forEach(AbstractWeatherDevice::processChanges));
    }

}
