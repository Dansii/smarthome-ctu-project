package cz.cvut.fel.omo.SmartHome.report;

/**
 * Abstract class, tha base of all reports.
 *
 */
public abstract class AbstractReport {

    public abstract void accept(ReportGenerator generator);

}
