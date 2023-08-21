package cz.cvut.fel.omo.SmartHome.report;

import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.home.Floor;
import cz.cvut.fel.omo.SmartHome.home.House;
import java.util.List;


/**
 * Class for House configuration report
 *
 */
public class HouseConfigurationReport extends AbstractReport {

    private final House house;

    public HouseConfigurationReport() {
        this.house = House.getInstance();
    }


    /**
     * Generates a string that contains information about house configuration
     * @return string with report
     */
    public String report(){

        StringBuilder sb = new StringBuilder();

        sb.append("House configuration:\n\n");

        sb.append("Number of floors: ")
                .append(house.getFloors().size())
                .append("\n");

        List<Floor> floors = house.getFloors();
        for(int i = 0; i < floors.size(); i++) {

            sb.append(i + 1).append(" Floor:\n");

            sb.append(" Temperature: ")
                    .append(floors.get(i).getFloorTemp());

            sb.append("; Wind speed: ")
                    .append(floors.get(i).getWindSpeed());

            sb.append("; Sunshine: ")
                    .append(floors.get(i).getSunshine())
                    .append("\n");

            List<AbstractActionDevice> actionDevices = floors.get(i).getActionDevices();

            sb.append(" Number of action devices on the floor: ")
                    .append(actionDevices.size())
                    .append("\n");

            if(actionDevices.size() > 0){

                sb.append(" Devices: \n");

                for (AbstractActionDevice d : actionDevices) {

                    sb.append("  ")
                            .append(d.getDeviceName())
                            .append("\n");

                }
            }

            List<AbstractWeatherDevice> weatherDevices = floors.get(i).getWeatherDevices();

            sb.append(" Number of weather devices on the floor: ")
                    .append(weatherDevices.size())
                    .append("\n");

            if(weatherDevices.size() > 0){

                sb.append(" Devices: \n");

                for (AbstractWeatherDevice d : weatherDevices) {

                    sb.append("  ")
                            .append(d.getDeviceName())
                            .append("\n");

                }
            }

        }

        List<Creature> creatures = House.getInstance().getCreatures();
        if(!creatures.isEmpty()){

            sb.append("\nLiving in the house:\n");

            for(Creature c: creatures){

                sb.append(" ")
                        .append(c.getRole())
                        .append(" ")
                        .append(c.getName())
                        .append("\n");

            }
        } else {
            sb.append("\nNobody lives here.");
        }

        return sb.toString();
    }


    @Override
    public void accept(ReportGenerator generator) {
        generator.generateHouseConfigurationReport(this);
    }
}
