package cz.cvut.fel.omo.SmartHome.devices.alarms;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Abstract class for listeners, that will remind house about alarm activation.
 * @param <T> Child of AbstractAlarmEvent, that represents fire/water/gas alarm event
 */
@Component
public abstract class AlarmListener<T extends AbstractAlarmEvent> implements ApplicationListener<T> {

    @Override
    public abstract void onApplicationEvent(T event);
}
