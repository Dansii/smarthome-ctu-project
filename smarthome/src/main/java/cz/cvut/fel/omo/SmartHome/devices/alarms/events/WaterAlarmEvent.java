package cz.cvut.fel.omo.SmartHome.devices.alarms.events;

import cz.cvut.fel.omo.SmartHome.EmergencyCenterVisitor;
import cz.cvut.fel.omo.SmartHome.devices.alarms.AbstractAlarmEvent;

public class WaterAlarmEvent extends AbstractAlarmEvent {

    public WaterAlarmEvent(Object source) {
        super(source);
    }

    @Override
    public void accept(EmergencyCenterVisitor emergencyCenterVisitor) {
        emergencyCenterVisitor.help(this);
    }

}
