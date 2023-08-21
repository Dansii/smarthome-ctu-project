package cz.cvut.fel.omo.SmartHome.devices;

import cz.cvut.fel.omo.SmartHome.home.Floor;
import cz.cvut.fel.omo.SmartHome.util.RandomUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractDevice {

    protected Floor floor;
    protected String deviceName;
    protected int condition;
    protected Instruction instruction;
    protected Consumption consumption;

    public AbstractDevice(Floor floor, String deviceName, Consumption consumption) {
        this.floor = floor;
        this.deviceName = deviceName;
        this.consumption = consumption;
        condition = 500;
        instruction = new Instruction();
    }

    /**
     * Determines if the child can use it or not.
     * @return true if can
     *         false if not
     */
    public abstract boolean isEntertaining();

    /**
     * Method calculating consuming of device.
     */
    public abstract void consume();

    /**
     * Method repairs the device condition.
     */
    public void repairDevice() {
        condition += RandomUtil.getRandomIntInRange(50, 250);
    }

    public boolean isBroken() {
        return condition <= 15;
    }

    /**
     * Method calculating total consuming of device.
     * @param onThe multiply constant
     */
    public void consumes(double onThe) {
        consumption.consumes(onThe * consumption.getConsumption());
    }

}
