package de.luh.sim.java13.ue3.simulationmodel;

import de.luh.sim.java13.ue3.simulatorcore.BasicSimulator;

/**
 * Reiht einen neuen Job in die erste Warteschlange ein. Erzeugt falls notwendig
 * neue Abgänge.
 */
public class Ankunft implements IEreignis {

	/** Zeitpunkt zu dem dieses Ereignis ausgeführt wird. */
	private final double ausfuehrungszeit;

	/** Debug Anzeige */
	private boolean debug;

	/** Anzahl an neunen Ankünften pro Zeiteinheiten */
	private double ankunftsrate;

	/**
	 * Erzeugt eine neue Ankunft
	 * 
	 * @param simtime
	 *            Zeitpunkt zu dem dieses Ereignis ausgeführt wird.
	 * @param debug
	 *            Debuganzeige ein oder aus.
	 * @param ankunftsrate
	 *            Ankunftsrate
	 */
	public Ankunft(double simtime, boolean debug, double ankunftsrate) {
		this.ausfuehrungszeit = simtime;
		this.debug = debug;
		this.ankunftsrate = ankunftsrate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.luh.sim.java13.ue3.simulationmodel.IEreignis#ereignisAusfuehren(de
	 * .luh.sim.java13.ue3.simulatorcore.BasicSimulator)
	 */
	public void ereignisAusfuehren(BasicSimulator simulator) {
		if (debug) {
			System.out.println("Ankunft");
		}
		// simulator.aktualisiereWarteschlangenlaenge();
		// Entferne dieses Ereignis aus der EreignisListe
		simulator.erstesEreignisEntfernen();

		// Nächste Ankunft erzeugen
		IEreignis naechsteAnkunft = new Ankunft(simulator.getSimulationszeit()
				+ (Math.random() / 2 + 0.75) * (1 / ankunftsrate), debug,
				ankunftsrate);
		// nicht vergessen, das ereignis auch zur liste hinzuzufügen
		simulator.neuesEreignisIntegrieren(naechsteAnkunft);

		// Dann muss noch der Zustand der Simulation angepasst werden,
		// hier gibts ja nur eine Warteschlange
		ISimulationEntity entity = simulator.getSimulationEntity(0);
		entity.setZustand(entity.getZustand() + 1);

		if (simulator.getSimulationEntity(0).getZustand() == 1) {
			// Falls das gerade eingefuegte Ereignis das einzige ist, dann muss
			// auch der Abgang erzeugt werden.
			IEreignis naechsterAbgang = new Abgang(
					simulator.getSimulationszeit() + (Math.random() / 2 + 0.75)
							* (1 / entity.getBedienrate()), debug, 0);
			simulator.neuesEreignisIntegrieren(naechsterAbgang);

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.luh.sim.java13.ue3.simulationmodel.IEreignis#getAusfuehrungszeit()
	 */
	public double getAusfuehrungszeit() {
		return ausfuehrungszeit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(IEreignis o) {
		if (getAusfuehrungszeit() < o.getAusfuehrungszeit()) {
			return -1;
		} else if (o.getAusfuehrungszeit() < getAusfuehrungszeit()) {
			return 1;
		}
		return 0;
	}

}
