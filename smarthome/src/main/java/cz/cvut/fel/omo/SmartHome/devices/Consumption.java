package cz.cvut.fel.omo.SmartHome.devices;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Consumption {

    private ConsumptionType consumptionType;

    private double totalConsumption;

    private double consumption;

    public Consumption(ConsumptionType consumptionType, double consumption) {
        this.totalConsumption = 0;
        this.consumptionType = consumptionType;
        setConsumption(consumption);
    }
    /**
     * Method calculating total consuming of device.
     */
    public void consumes(double onThe) {
        totalConsumption += onThe;
    }
}
