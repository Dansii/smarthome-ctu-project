package cz.cvut.fel.omo.SmartHome.factory;

import cz.cvut.fel.omo.SmartHome.sensor.SensorController;
import cz.cvut.fel.omo.SmartHome.world.AlarmEventsPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class BeanFactory {

    private static AlarmEventsPublisher alarmEventsPublisher;

    @Autowired
    public BeanFactory(ApplicationEventPublisher applicationEventPublisher) {
        alarmEventsPublisher = new AlarmEventsPublisher(applicationEventPublisher);
    }

    public static AlarmEventsPublisher getAlarmEventPublisher(){
        return alarmEventsPublisher;
    }

}
