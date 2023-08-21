package cz.cvut.fel.omo.SmartHome.devices.alarms.events;

import cz.cvut.fel.omo.SmartHome.EmergencyCenterVisitor;
import cz.cvut.fel.omo.SmartHome.devices.alarms.AbstractAlarmEvent;

public class GasAlarmEvent extends AbstractAlarmEvent {

    public GasAlarmEvent(Object source) {
        super(source);
    }

    @Override
    public void accept(EmergencyCenterVisitor emergencyCenterVisitor) {
        emergencyCenterVisitor.help(this);
    }

}
