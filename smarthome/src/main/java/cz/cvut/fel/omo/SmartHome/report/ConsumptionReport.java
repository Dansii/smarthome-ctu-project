package cz.cvut.fel.omo.SmartHome.report;

import cz.cvut.fel.omo.SmartHome.devices.AbstractDevice;
import cz.cvut.fel.omo.SmartHome.devices.ConsumptionType;
import cz.cvut.fel.omo.SmartHome.home.Floor;
import cz.cvut.fel.omo.SmartHome.home.House;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for Consumption report
 *
 */
public class ConsumptionReport extends AbstractReport {
    private static int electricity_cost;
    private static int heating_cost;
    private final House house;
    private final List<AbstractDevice> allDevices;


    public ConsumptionReport() {
        this.house = House.getInstance();
        allDevices = new ArrayList<>();
        electricity_cost = 0;
        heating_cost = 0;
    }


    /**
     * Generates a string that contains information about house consumption including financial conclusions
     * @return string with report
     */
    public String report() {
        StringBuilder sb = new StringBuilder();
        String measure = "";
        List<Floor> floors = house.getFloors();
        for (Floor f : floors) {
            allDevices.addAll(f.getActionDevices());
            allDevices.addAll(f.getWeatherDevices());
        }
        if (allDevices.isEmpty()) {
            sb.append("There is no devices");
        } else {
            for (AbstractDevice d : allDevices) {
                if (d.getConsumption().getConsumptionType() != null) {
                    if (d.getConsumption().getConsumptionType() == ConsumptionType.ELECTRICITY) {
                        measure = " W ";
                        double cost = d.getConsumption().getTotalConsumption() * 0.005;
                        electricity_cost += cost;
                    } else if (d.getConsumption().getConsumptionType() == ConsumptionType.HEATING) {
                        measure = " cal ";
                        double cost = d.getConsumption().getTotalConsumption() * 0.007;
                        heating_cost += cost;
                    }
                }
                sb.append("Consumption of ")
                        .append(d.getDeviceName()).append(":\n")
                        .append(" Total: ")
                        .append(d.getConsumption().getTotalConsumption())
                        .append(measure).append("\n");
            }
        }

        sb.append("\nTotal cost:\n  Electricity: ")
                .append(electricity_cost)
                .append(" Kc \n")
                .append("  Heating: ")
                .append(heating_cost)
                .append(" Kc");

        return sb.toString();
    }


    @Override
    public void accept(ReportGenerator generator) {
        generator.generateConsumptionReport(this);
    }
}
