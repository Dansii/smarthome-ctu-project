package cz.cvut.fel.omo.SmartHome.factory;

import cz.cvut.fel.omo.SmartHome.HouseSize;
import cz.cvut.fel.omo.SmartHome.alive.Creature;
import cz.cvut.fel.omo.SmartHome.alive.Role;
import cz.cvut.fel.omo.SmartHome.devices.Consumption;
import cz.cvut.fel.omo.SmartHome.devices.ConsumptionType;
import cz.cvut.fel.omo.SmartHome.devices.action.AbstractActionDevice;
import cz.cvut.fel.omo.SmartHome.devices.action.common.*;
import cz.cvut.fel.omo.SmartHome.devices.action.pet.AutomaticFeeder;
import cz.cvut.fel.omo.SmartHome.devices.action.pet.MechanicalToy;
import cz.cvut.fel.omo.SmartHome.devices.action.sport.Bicycle;
import cz.cvut.fel.omo.SmartHome.devices.action.sport.BikeTrainer;
import cz.cvut.fel.omo.SmartHome.devices.action.sport.RowingMachine;
import cz.cvut.fel.omo.SmartHome.devices.action.sport.Treadmill;
import cz.cvut.fel.omo.SmartHome.devices.weather.AbstractWeatherDevice;
import cz.cvut.fel.omo.SmartHome.devices.weather.WeatherDeviceSetUp;
import cz.cvut.fel.omo.SmartHome.devices.weather.sun.insideBlinds.InsideBlinds;
import cz.cvut.fel.omo.SmartHome.devices.weather.temperature.splitSystem.SplitSystem;
import cz.cvut.fel.omo.SmartHome.devices.weather.wind.outsideBlinds.OutsideBlinds;
import cz.cvut.fel.omo.SmartHome.home.Floor;
import cz.cvut.fel.omo.SmartHome.home.House;
import cz.cvut.fel.omo.SmartHome.world.World;

import java.util.ArrayList;
import java.util.List;

public class HomeFactory {

    private static int indexDevice = 0;

    public static void setUpHouse(int adultsNumber, int childNumbers, int petsNumber, HouseSize houseSize) {
        House house = House.getInstance();

        List<Floor> floors = new ArrayList<>();
        switch (houseSize) {
            case Small:
                floors = initFloorsSmall();
                break;
            case Big:
                floors = initFloorsBig();
                break;
        }
        house.setFloors(floors);

        List<Creature> creatures = initCreatures(adultsNumber, childNumbers, petsNumber);
        house.setCreatures(creatures);
    }

    private static List<Creature> initCreatures(int adultsNumber, int childNumbers, int petsNumber) {
        List<Creature> creatures = new ArrayList<>();
        for (int i = 0; i < adultsNumber; i++) {
            creatures.add(new Creature(Role.ADULT));
        }
        for (int i = 0; i < childNumbers; i++) {
            creatures.add(new Creature(Role.CHILD));
        }
        for (int i = 0; i < petsNumber; i++) {
            creatures.add(new Creature(Role.ANIMAL));
        }
        return creatures;
    }

    private static List<Floor> initFloorsSmall() {
        List<Floor> floors = new ArrayList<>();
        World instance = World.getInstance();
        Floor floor = new Floor(instance.getOutsideTemp(), instance.getOutsideSunshine(), instance.getOutsideWindspeed());

        List<AbstractActionDevice> actionDevices = initActionDevicesSmall(floor);
        floor.setActionDevices(actionDevices);

        List<AbstractWeatherDevice> weatherDevices = initWeatherDevicesFloor(floor);
        floor.setWeatherDevices(weatherDevices);

        floors.add(floor);

        return floors;
    }

    private static List<Floor> initFloorsBig() {

        List<Floor> floors = new ArrayList<>();
        World instance = World.getInstance();
        List<AbstractActionDevice> actionDevices;
        Floor floor;

        floor = new Floor(instance.getOutsideTemp(), instance.getOutsideSunshine(), instance.getOutsideWindspeed());
        actionDevices = new ArrayList<>();

        actionDevices.add(createGasStove(floor));
        actionDevices.add(createTurnable(floor));
        actionDevices.add(createTV(floor));
        actionDevices.add(createTV(floor));
        actionDevices.add(createAutomaticFeeder(floor));
        actionDevices.add(createBikeTrainer(floor));
        floor.setActionDevices(actionDevices);
        floor.setWeatherDevices(initWeatherDevicesFloor(floor));

        floors.add(floor);

        floor = new Floor(instance.getOutsideTemp(), instance.getOutsideSunshine(), instance.getOutsideWindspeed());
        actionDevices = new ArrayList<>();

        actionDevices.add(createTV(floor));
        actionDevices.add(createTurnable(floor));
        actionDevices.add(createElectromobile(floor));
        actionDevices.add(createTreadmill(floor));
        actionDevices.add(createProjector(floor));
        actionDevices.add(createBikeTrainer(floor));
        actionDevices.add(createCoffeeMachine(floor));
        actionDevices.add(createRowingMachine(floor));
        floor.setActionDevices(actionDevices);
        floor.setWeatherDevices(initWeatherDevicesFloor(floor));

        floors.add(floor);

        actionDevices.add(createGasStove(floor));
        actionDevices.add(createTurnable(floor));
        actionDevices.add(createAutomaticFeeder(floor));
        actionDevices.add(createTV(floor));
        actionDevices.add(createMicrowave(floor));
        actionDevices.add(createBicycle(floor));
        actionDevices.add(createMechanicalToy(floor));
        floor.setActionDevices(actionDevices);
        floor.setWeatherDevices(initWeatherDevicesFloor(floor));

        floors.add(floor);

        return floors;
    }

    private static List<AbstractActionDevice> initActionDevicesSmall(Floor floor) {
        List<AbstractActionDevice> actionDevices = new ArrayList<>();
        actionDevices.add(createGasStove(floor));
        actionDevices.add(createTurnable(floor));
        actionDevices.add(createTV(floor));
        actionDevices.add(createAutomaticFeeder(floor));
        actionDevices.add(createBicycle(floor));
        actionDevices.add(createBikeTrainer(floor));
        return actionDevices;
    }

    private static List<AbstractWeatherDevice> initWeatherDevicesFloor(Floor floor) {
        List<AbstractWeatherDevice> weatherDevices = new ArrayList<>();
        WeatherDeviceSetUp weatherDeviceSetUp = new WeatherDeviceSetUp(25, 15, 15, 15);

        weatherDevices.add(createSplitSystem(floor, weatherDeviceSetUp));
        weatherDevices.add(createInsideBlinds(floor, weatherDeviceSetUp));
        weatherDevices.add(createOutsideBlinds(floor, weatherDeviceSetUp));

        return weatherDevices;
    }

    private static Microwave createMicrowave(Floor floor) {
        return new Microwave(floor, "Microwave-", 5, new Consumption(ConsumptionType.ELECTRICITY, 1));
    }

    private static GasStove createGasStove(Floor floor) {
        return new GasStove(floor, "GasStove-" + ++indexDevice, 5, new Consumption(ConsumptionType.HEATING, 1));
    }

    private static Turntable createTurnable(Floor floor) {
        return new Turntable(floor, "Turntable-" + ++indexDevice, 5, new Consumption(ConsumptionType.ELECTRICITY, 1));
    }

    private static TV createTV(Floor floor) {
        return new TV(floor, "TV-"+ ++indexDevice, 5, new Consumption(ConsumptionType.ELECTRICITY, 1));
    }

    private static CoffeeMachine createCoffeeMachine(Floor floor) {
        return new CoffeeMachine(floor, "CoffeeMachine-"+ ++indexDevice, 5, new Consumption(ConsumptionType.ELECTRICITY, 1));
    }

    private static BikeTrainer createBikeTrainer(Floor floor) {
        return new BikeTrainer(floor, "BikeTrainer-"+ ++indexDevice, 5, new Consumption(ConsumptionType.ELECTRICITY, 0.1));
    }

    private static Treadmill createTreadmill(Floor floor) {
        return new Treadmill(floor, "Treadmill-"+ ++indexDevice, 5, new Consumption(ConsumptionType.ELECTRICITY, 0.5));
    }

    private static InsideBlinds createInsideBlinds(Floor floor, WeatherDeviceSetUp weatherDeviceSetUp) {
        return new InsideBlinds(floor, "InsideBlinds-"+ ++indexDevice,  new Consumption(ConsumptionType.ELECTRICITY, 0.5), weatherDeviceSetUp);
    }

    private static OutsideBlinds createOutsideBlinds(Floor floor, WeatherDeviceSetUp weatherDeviceSetUp) {
        return new OutsideBlinds(floor, "OutsideBlinds-"+ ++indexDevice,  new Consumption(ConsumptionType.ELECTRICITY, 0.5), weatherDeviceSetUp);
    }

    private static SplitSystem createSplitSystem(Floor floor, WeatherDeviceSetUp weatherDeviceSetUp) {
        return new SplitSystem(floor, "SplitSystem-"+ ++indexDevice, new Consumption(ConsumptionType.ELECTRICITY, 3), weatherDeviceSetUp);
    }

    private static Electromobile createElectromobile(Floor floor) {
        return new Electromobile(floor, "Electromobile-"+ ++indexDevice, 20, new Consumption(ConsumptionType.ELECTRICITY, 5));
    }

    private static Projector createProjector(Floor floor) {
        return new Projector(floor, "Projector-"+ ++indexDevice, 5, new Consumption(ConsumptionType.ELECTRICITY, 2));
    }

    private static AutomaticFeeder createAutomaticFeeder(Floor floor) {
        return new AutomaticFeeder(floor, "AutomaticFeeder-"+ ++indexDevice, 3, new Consumption(ConsumptionType.ELECTRICITY, 1));
    }

    private static MechanicalToy createMechanicalToy(Floor floor) {
        return new MechanicalToy(floor, "MechanicalToy-"+ ++indexDevice, 3, new Consumption(ConsumptionType.ELECTRICITY, 1));
    }

    private static RowingMachine createRowingMachine(Floor floor) {
        return new RowingMachine(floor, "RowingMachine-"+ ++indexDevice, 3, new Consumption(ConsumptionType.ELECTRICITY, 0.1));
    }

    private static Bicycle createBicycle(Floor floor) {
        return new Bicycle(floor, "Bicycle-"+ ++indexDevice, 3, new Consumption(ConsumptionType.NONE, 0.1));
    }

}
