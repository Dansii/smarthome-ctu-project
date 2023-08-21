package cz.cvut.fel.omo.SmartHome.devices.alarms.events;

import cz.cvut.fel.omo.SmartHome.EmergencyCenterVisitor;
import cz.cvut.fel.omo.SmartHome.devices.alarms.AbstractAlarmEvent;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;

public class FireAlarmEvent extends AbstractAlarmEvent {

    public FireAlarmEvent(Object source) {
        super(source);
    }

    @Override
    public void accept(EmergencyCenterVisitor emergencyCenterVisitor) {
        EventsKeeper.getInstance().addStrategyEvent("Fire Alarm", null);
        emergencyCenterVisitor.help(this);
    }

}
