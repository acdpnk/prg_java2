package de.luh.sim.java13.ue3.simulationmodel;

// TODO: Auto-generated Javadoc
/**
 * Eine Warteschlange, an der Jobs eingereit und abgearbeitet werden können.
 */
public class Warteschlange implements ISimulationEntity {

	/** Anzahl der momentanen Jobs in der Warteschlange. */
	private int anzahlJobs;
	/** Anzahl der Jobs, die pro Zeiteinheit, abgefertigt werden. */
	private double bedienrate;
	
	/** Name der Warteschlange. */
	private String name;
	
	/**
	 * Instanziiert eine leere Warteschlange.
	 *
	 * @param bedienrate Anzahl der Jobs, die pro Zeiteinheit, abgefertigt werden.
	 * @param name Name der Warteschlange.
	 */
	public Warteschlange(double bedienrate, String name) {
		anzahlJobs = 0;
		this.bedienrate = bedienrate;
		this.name = name;
	}
	
	
	/* (non-Javadoc)
	 * @see de.luh.sim.java13.ue3.simulationmodel.ISimulationEntity#getZustand()
	 */
	public int getZustand() {
		return anzahlJobs;
	}

	/* (non-Javadoc)
	 * @see de.luh.sim.java13.ue3.simulationmodel.ISimulationEntity#setZustand(int)
	 */
	public void setZustand(int neuerZustand) {
		anzahlJobs = neuerZustand;
	}

	/* (non-Javadoc)
	 * @see de.luh.sim.java13.ue3.simulationmodel.ISimulationEntity#getBedienrate()
	 */
	@Override
	public double getBedienrate() {
		return bedienrate;
	}

	/* (non-Javadoc)
	 * @see de.luh.sim.java13.ue3.simulationmodel.ISimulationEntity#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

}
