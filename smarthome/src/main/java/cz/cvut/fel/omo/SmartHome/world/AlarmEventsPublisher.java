package cz.cvut.fel.omo.SmartHome.world;

import cz.cvut.fel.omo.SmartHome.devices.alarms.events.FireAlarmEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class AlarmEventsPublisher {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final Random rand = new Random();

    @Autowired
    public AlarmEventsPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void generateAlarms() {
        int i = rand.nextInt(10000);
        int fire = rand.nextInt(10000);
        int water = rand.nextInt(10000);
        int gas = rand.nextInt(10000);
        if (i == fire) {
            FireAlarmEvent customSpringEvent = new FireAlarmEvent(this);
            applicationEventPublisher.publishEvent(customSpringEvent);
        } else if (i == water) {
            FireAlarmEvent customSpringEvent = new FireAlarmEvent(this);
            applicationEventPublisher.publishEvent(customSpringEvent);
        } else if (i == gas) {
            FireAlarmEvent customSpringEvent = new FireAlarmEvent(this);
            applicationEventPublisher.publishEvent(customSpringEvent);
        }
    }

}