package cz.cvut.fel.omo.SmartHome;

import cz.cvut.fel.omo.SmartHome.devices.alarms.AbstractAlarmEvent;
import cz.cvut.fel.omo.SmartHome.devices.alarms.events.FireAlarmEvent;
import cz.cvut.fel.omo.SmartHome.devices.alarms.events.GasAlarmEvent;
import cz.cvut.fel.omo.SmartHome.devices.alarms.events.WaterAlarmEvent;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;
import cz.cvut.fel.omo.SmartHome.util.RandomUtil;
import lombok.Getter;


public class EmergencyCenterVisitor {

    @Getter
    private int timeUntilRepair = 0;

    public void helpAbstract(AbstractAlarmEvent abstractAlarmEvent){
        abstractAlarmEvent.accept(this);
    }

    public void help(FireAlarmEvent fireAlarmEvent){
        timeUntilRepair += RandomUtil.getRandomIntInRange(1, 50);
        EventsKeeper.getInstance().addStrategyEvent("Fire alarm! Called firefighters", null);
    }

    public void help(WaterAlarmEvent waterAlarmEvent){
        timeUntilRepair += RandomUtil.getRandomIntInRange(1, 50);
        EventsKeeper.getInstance().addStrategyEvent("Water alarm! Called help", null);
    }

    public void help(GasAlarmEvent gasAlarmEvent){
        timeUntilRepair += RandomUtil.getRandomIntInRange(1, 50);
        EventsKeeper.getInstance().addStrategyEvent("Gas alarm! Called emergency", null);
    }

}
