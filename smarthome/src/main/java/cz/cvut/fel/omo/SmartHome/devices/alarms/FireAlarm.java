package cz.cvut.fel.omo.SmartHome.devices.alarms;

import cz.cvut.fel.omo.SmartHome.devices.alarms.events.FireAlarmEvent;
import cz.cvut.fel.omo.SmartHome.home.House;
import org.springframework.stereotype.Component;

@Component
public class FireAlarm extends AlarmListener<FireAlarmEvent> {

    @Override
    public void onApplicationEvent(FireAlarmEvent event) {
        House.getInstance().addAlarm(event);
    }

}
