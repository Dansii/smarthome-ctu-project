package cz.cvut.fel.omo.SmartHome.devices.weather;

import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.Consumption;
import cz.cvut.fel.omo.SmartHome.devices.TurnableDevicesInterface;
import cz.cvut.fel.omo.SmartHome.home.Floor;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractWeatherDevice extends AbstractDevice implements TurnableDevicesInterface {

    protected AbstractWeatherDeviceState state;

    private int timeUntilRepair = 0;

    private boolean isBroken;

    private WeatherDeviceSetUp weatherDeviceSetUp;

    public AbstractWeatherDevice(Floor floor, String deviceName, Consumption consumption, WeatherDeviceSetUp weatherDeviceSetUp) {
        super(floor, deviceName, consumption);
        this.weatherDeviceSetUp = weatherDeviceSetUp;
    }

    /**
     * Method that process outside changes depending on the current state
     */
    public void processChanges() {
        EventsKeeper.getInstance().addWeatherEvent("'s sensor detected weather changes", this);
        state.processChanges(this);
    }

    @Override
    public void consume(){
        state.consume(this);
    }

    /**
     *  Method that is used for changing weather devices settings.
     * @param weatherDeviceSetUp dto
     */
    public void newSetUp(WeatherDeviceSetUp weatherDeviceSetUp){
        EventsKeeper.getInstance().addWeatherEvent("set up",this);
        this.weatherDeviceSetUp = weatherDeviceSetUp;
    }
}
