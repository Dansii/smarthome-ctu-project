package cz.cvut.fel.omo.SmartHome.devices.alarms;

import cz.cvut.fel.omo.SmartHome.devices.alarms.events.GasAlarmEvent;
import cz.cvut.fel.omo.SmartHome.home.House;
import org.springframework.stereotype.Component;

@Component
public class GasAlarm extends AlarmListener<GasAlarmEvent> {

    @Override
    public void onApplicationEvent(GasAlarmEvent event) {
        House.getInstance().addAlarm(event);
    }

}
