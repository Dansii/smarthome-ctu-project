package cz.cvut.fel.omo.SmartHome;

import cz.cvut.fel.omo.SmartHome.devices.alarms.FireAlarm;
import cz.cvut.fel.omo.SmartHome.factory.HomeFactory;
import cz.cvut.fel.omo.SmartHome.home.House;
import cz.cvut.fel.omo.SmartHome.world.World;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigInteger;
import java.util.Scanner;

@SpringBootApplication
public class SmartHomeApplication {

	public static void main(String[] args) {
		FireAlarm fireAlarm = new FireAlarm();

		SpringApplication.run(SmartHomeApplication.class, args);
		Scanner myObj = new Scanner(System.in);
		System.out.println("Enter size of house: 1 - Small, 2 - Big");
		int size = myObj.nextInt();
		World world = World.getInstance();
		House house = House.getInstance();
		world.setOutsideWeather(20, 10, 10);
		switch (size){
			case 1:
				HomeFactory.setUpHouse(2, 1, 1, HouseSize.Small);

				// i = 5 min
				for(int i = 0; i < 10000; i++){

					world.doWorldChanges();

					house.mainAction();

				}

				house.generateReports();
				break;
			case 2:
				HomeFactory.setUpHouse(4, 3, 3, HouseSize.Big);
				for(int i = 0; i < 10000; i++){

					world.doWorldChanges();

					house.mainAction();

				}

				house.generateReports();
				break;
		}
	}

}
