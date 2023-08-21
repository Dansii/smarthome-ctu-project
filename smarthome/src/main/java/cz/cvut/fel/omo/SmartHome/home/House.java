package cz.cvut.fel.omo.SmartHome.home;

import cz.cvut.fel.omo.SmartHome.ControlPanel;
import cz.cvut.fel.omo.SmartHome.HomeEmergencyStrategy;
import cz.cvut.fel.omo.SmartHome.HomeNormalStrategy;
import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.devices.TurnableDevicesInterface;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.devices.alarms.AbstractAlarmEvent;
import cz.cvut.fel.omo.SmartHome.report.*;
import cz.cvut.fel.omo.SmartHome.sensor.SensorController;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Data
public class House {

    private final static House instance = new House();
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private ControlPanel controlPanel;

    private double windSpeed;
    private List<Floor> floors;
    private List<AbstractAlarmEvent> alarmEvents = new ArrayList<>();
    private List<AbstractActionDevice> brokenDevices = new ArrayList<>();
    private ReportGenerator reportGenerator;
    private List<Creature> creatures;
    private SensorController sensorController;


    private House() {
        reportGenerator = new ReportGenerator();
        controlPanel = new ControlPanel(new HomeNormalStrategy());
    }

    public static House getInstance() {
        return instance;
    }

    public double getTemp() {
        return floors.stream().mapToDouble(Floor::getFloorTemp).summaryStatistics().getAverage();
    }

    public void start() {
        floors.forEach(t -> t.getTurnableDevices()
                .forEach(TurnableDevicesInterface::turnOn));
    }

    public void stop() {
        floors.forEach(t -> t.getTurnableDevices()
                .forEach(TurnableDevicesInterface::turnOff));
    }

    public void generateReports() {
        reportGenerator.generate(new HouseConfigurationReport());
        reportGenerator.generate(new ActivityAndUsageReport());
        reportGenerator.generate(new EventReport());
        reportGenerator.generate(new ConsumptionReport());

    }

    public void mainAction() {
        if (!alarmEvents.isEmpty() && !controlPanel.isEmergency()) {
            controlPanel = new ControlPanel(new HomeEmergencyStrategy());
            stop();
        } else if (alarmEvents.isEmpty() && controlPanel.isEmergency()) {
            controlPanel = new ControlPanel(new HomeNormalStrategy());
            start();
        }
        controlPanel.mainCycle(creatures);
    }

    public void addAlarm(AbstractAlarmEvent event) {
        this.alarmEvents.add(event);
    }

    public void addBrokenDevice(AbstractActionDevice abstractDevice) {
        brokenDevices.add(abstractDevice);
    }

    public void removeBrokenDevice(AbstractActionDevice abstractDevice) {
        brokenDevices.remove(abstractDevice);
    }

    public void setFloors(List<Floor> floors){
        this.floors = floors;
        this.sensorController = new SensorController(floors);
    }

}
