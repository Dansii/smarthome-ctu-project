package cz.cvut.fel.omo.SmartHome;

import cz.cvut.fel.omo.SmartHome.alive.Creature;

import java.util.List;

public interface HomeStrategy {

    void mainCycle(List<Creature> creatures);

    boolean isEmergency();

}
