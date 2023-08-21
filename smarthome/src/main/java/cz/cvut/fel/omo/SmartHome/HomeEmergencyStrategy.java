package cz.cvut.fel.omo.SmartHome;

import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.devices.alarms.AbstractAlarmEvent;
import cz.cvut.fel.omo.SmartHome.home.House;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;

import java.util.ArrayList;
import java.util.List;

public class HomeEmergencyStrategy implements HomeStrategy{

    private int timeForResolve;
    private final House house = House.getInstance();

    public HomeEmergencyStrategy(){
        List<AbstractAlarmEvent> alarmEvents = house.getAlarmEvents();
        EmergencyCenterVisitor emergencyCenterVisitor = new EmergencyCenterVisitor();
        for(AbstractAlarmEvent event: alarmEvents){
            emergencyCenterVisitor.helpAbstract(event);
        }
        timeForResolve = emergencyCenterVisitor.getTimeUntilRepair();
        EventsKeeper.getInstance().addStrategyEvent("Emergency strategy is on!", null);
    }

    @Override
    public void mainCycle(List<Creature> creatures) {
        timeForResolve--;
        if(timeForResolve == 0){
            EventsKeeper.getInstance().addStrategyEvent("Emergency strategy is off!", null);
            house.setAlarmEvents(new ArrayList<>());
        }
    }

    @Override
    public boolean isEmergency() {
        return true;
    }

}
