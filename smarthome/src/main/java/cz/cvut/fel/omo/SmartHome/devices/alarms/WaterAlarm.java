package cz.cvut.fel.omo.SmartHome.devices.alarms;

import cz.cvut.fel.omo.SmartHome.devices.alarms.events.WaterAlarmEvent;
import cz.cvut.fel.omo.SmartHome.home.House;
import org.springframework.stereotype.Component;

@Component
public class WaterAlarm extends AlarmListener<WaterAlarmEvent> {

    @Override
    public void onApplicationEvent(WaterAlarmEvent event) {
        House.getInstance().addAlarm(event);
    }

}
