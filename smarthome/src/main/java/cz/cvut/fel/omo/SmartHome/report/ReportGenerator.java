package cz.cvut.fel.omo.SmartHome.report;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Class for generating reports and writing them down to the .txt file
 *
 */
public class ReportGenerator {

    public void generate(AbstractReport report){
        report.accept(this);
    }

    public void generateHouseConfigurationReport(HouseConfigurationReport houseConfigurationReport){
        try {
            String report = houseConfigurationReport.report();
            FileWriter myWriter = new FileWriter("HouseConfigurationReport.txt");
            myWriter.write(report);
            myWriter.close();
            System.out.println("House configuration has been successfully wrote to the file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


    public void generateActivityAndUsageReport(ActivityAndUsageReport activityAndUsagesReport){
        try {
            activityAndUsagesReport.setEvents(EventsKeeper.getInstance().getEvents());
            String report = activityAndUsagesReport.report();
            FileWriter myWriter = new FileWriter("ActivityAndUsageReport.txt");
            myWriter.write(report);
            myWriter.close();
            System.out.println("Activity and usage report has been successfully wrote to the file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void generateEventReport(EventReport eventReport){
        try {
            eventReport.setEvents(EventsKeeper.getInstance().getEvents());
            String report = eventReport.report();
            FileWriter myWriter = new FileWriter("EventReport.txt");
            myWriter.write(report);
            myWriter.close();
            System.out.println("Event report has been successfully wrote to the file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public void generateConsumptionReport(ConsumptionReport consumptionReport){
        try {
            String report = consumptionReport.report();
            FileWriter myWriter = new FileWriter("ConsumptionReport.txt");
            myWriter.write(report);
            myWriter.close();
            System.out.println("Consumption report has been successfully wrote to the file");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
