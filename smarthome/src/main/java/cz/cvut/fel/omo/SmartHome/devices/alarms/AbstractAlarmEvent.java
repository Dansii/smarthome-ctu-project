package cz.cvut.fel.omo.SmartHome.devices.alarms;

import cz.cvut.fel.omo.SmartHome.EmergencyCenterVisitor;
import org.springframework.context.ApplicationEvent;

/**
 * Class represent AlarmEvent, that is sent to AlarmListeners classes.
 */
public abstract class AbstractAlarmEvent extends ApplicationEvent {

    public AbstractAlarmEvent(Object source) {
        super(source);
    }

    public abstract void accept(EmergencyCenterVisitor emergencyCenterVisitor);

}
