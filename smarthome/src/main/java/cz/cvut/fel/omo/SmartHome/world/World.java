package cz.cvut.fel.omo.SmartHome.world;

import cz.cvut.fel.omo.SmartHome.devices.weather.event.SeasonEnum;
import cz.cvut.fel.omo.SmartHome.devices.weather.event.WeatherEvent;
import cz.cvut.fel.omo.SmartHome.factory.BeanFactory;
import cz.cvut.fel.omo.SmartHome.home.House;
import cz.cvut.fel.omo.SmartHome.report.EventsKeeper;
import cz.cvut.fel.omo.SmartHome.util.RandomUtil;
import lombok.Getter;

public class World {

    private static SeasonEnum season = SeasonEnum.SUMMER;
    private final AlarmEventsPublisher alarmEventsPublisher;

    @Getter
    private double outsideTemp;
    @Getter
    private double outsideSunshine;
    @Getter
    private double outsideWindspeed;

    private double minOutsideTemp;
    private double maxOutsideTemp;
    private double minSunshine;
    private double maxSunshine;
    private double minWindspeed;
    private double maxWindspeed;

    private final static World instance = new World();

    private World() {
        this.alarmEventsPublisher = BeanFactory.getAlarmEventPublisher();
        setWorld(SeasonEnum.SUMMER);
    }

    public static World getInstance() {
        return instance;
    }

    public void setOutsideWeather(double outsideTemp, double outsideSunshine, double outsideWindspeed){
        this.outsideTemp = outsideTemp;
        this.outsideSunshine = outsideSunshine;
        this.outsideWindspeed = outsideWindspeed;
    }

    public void doWorldChanges() {

        WeatherEvent weatherEvent = generateWeatherEvent();

        House.getInstance().getFloors().forEach(t -> t.reactToWordChanges(weatherEvent));

        House.getInstance().getSensorController().processChanges();

        alarmEventsPublisher.generateAlarms();

    }

    public void setWorld(SeasonEnum season){
        switch (season){
            case SPRING:
                this.minOutsideTemp = 5;
                this.maxOutsideTemp = 15;
                this.minSunshine = 0;
                this.maxSunshine = 15;
                this.minWindspeed = 0;
                this.maxWindspeed = 10;
                break;
            case SUMMER:
                this.minOutsideTemp = 10;
                this.maxOutsideTemp = 30;
                this.minSunshine = 10;
                this.maxSunshine = 25;
                this.minWindspeed = 5;
                this.maxWindspeed = 15;
                break;
            case AUTUMN:
                this.minOutsideTemp = -2;
                this.maxOutsideTemp = 10;
                this.minSunshine = 0;
                this.maxSunshine = 8;
                this.minWindspeed = 2;
                this.maxWindspeed = 15;
                break;
            case WINTER:
                this.minOutsideTemp = -10;
                this.maxOutsideTemp = 5;
                this.minSunshine = 0;
                this.maxSunshine = 5;
                this.minWindspeed = 10;
                this.maxWindspeed = 20;
                break;
        }
    }

    private WeatherEvent generateWeatherEvent() {

        double temp = RandomUtil.getRandomDoubleInRange(0, 2);
        double sun = RandomUtil.getRandomDoubleInRange(0, 2);
        double wind = RandomUtil.getRandomDoubleInRange(0, 2);

        if (this.outsideTemp - temp < this.minOutsideTemp){
            this.outsideTemp += temp;
        } else if( this.outsideTemp + temp > this.maxOutsideTemp){
            this.outsideTemp -= temp;
            temp = -temp;
        } else{
            if(RandomUtil.getRandomBoolean()){
                this.outsideTemp+=temp;
            } else{
                this.outsideTemp-=temp;
                temp = -temp;
            }
        }

        if (this.outsideSunshine - sun < this.minSunshine){
            this.outsideSunshine += sun;
        } else if(this.outsideSunshine + sun > this.maxSunshine){
            this.outsideSunshine -= sun;
            sun = -sun;
        } else{
            if(RandomUtil.getRandomBoolean()){
                this.outsideSunshine+=sun;
            } else{
                this.outsideSunshine-=sun;
                sun = -sun;
            }
        }

        if (this.outsideWindspeed - wind < this.minWindspeed){
            this.outsideWindspeed += wind;
        } else if(this.outsideWindspeed + wind > this.maxWindspeed){
            this.outsideWindspeed -= wind;
            wind = - wind;
        } else{
            if(RandomUtil.getRandomBoolean()){
                this.outsideWindspeed+=wind;
            } else{
                this.outsideWindspeed-=wind;
                wind = - wind;
            }
        }

        EventsKeeper.getInstance().addStrategyEvent("Weather: "
                + (this.outsideTemp + temp)
                + " degrees, wind speed is "
                + (this.outsideWindspeed
                + wind) + ", sun UV is "
                + (this.outsideSunshine + sun), null);


        return new WeatherEvent(sun, wind, temp);
    }
}
