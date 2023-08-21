package cz.cvut.fel.omo.SmartHome.devices;

/**
 * The class represents the instructions for repairing the device.
 */
public class Instruction {

    private String descripton;

    public Instruction() {
        descripton = "Manual for device";
    }

    public String getDescripton() {
        return descripton;
    }

    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }
}
