package cz.cvut.fel.omo.SmartHome;

import cz.cvut.fel.omo.SmartHome.alive.Creature;

import java.util.List;

public class ControlPanel {

    private final HomeStrategy strategy;

    public ControlPanel(HomeStrategy strategy){
        this.strategy = strategy;
    }

    public void mainCycle(List<Creature> creatures){
        strategy.mainCycle(creatures);
    }

    public boolean isEmergency(){
        return strategy.isEmergency();
    }

}
