package de.luh.sim.java13.ue3.simulationmodel;

import de.luh.sim.java13.ue3.simulatorcore.BasicSimulator;

/**
 * Entfernt einen Job aus aus dem SimulationEntitiy. Erzeugt neue Abgänge.
 */
public class Abgang implements IEreignis {

	/** Zeitpunkt zu dem dieses Ereignis ausgeführt wird. */
	private final double ausfuehrungszeit;
	/** Debug Anzeige */
	private boolean debug;

	/** Zu bearbeitende Warteschlange */
	private int warteschlange;

	/**
	 * Instanziert eine neuen Abgang.
	 * 
	 * @param ausfuehrungszeit
	 *            Zeitpunkt zu dem dieses Ereignis ausgeführt wird.
	 * @param debug
	 *            Bei true ist die Debug-Anzeige eingeschaltet, bei false
	 *            ausgeschaltet.
	 * @param warteschlange
	 *            Id der Warteschlange auf die der Abgang ausgeführt werden
	 *            soll.
	 */
	public Abgang(double simtime, boolean debug, int warteschlange) {
		this.ausfuehrungszeit = simtime;
		this.debug = debug;
		this.warteschlange = warteschlange;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.luh.sim.java13.ue2.simulationmodel.IEreignis#ereignisAusfuehren(de
	 * .luh.sim.java13.ue2.simulatorcore.BasicSimulator)
	 */
	public void ereignisAusfuehren(BasicSimulator simulator) {
		if (debug) {
			System.out.println("Abgang");

		}
		// simulator.aktualisiereWarteschlangenlaenge();
		// Entferne dieses Ereignis aus der EreignisListe
		simulator.erstesEreignisEntfernen();
		ISimulationEntity simulationEntity = simulator
				.getSimulationEntity(warteschlange);
		simulationEntity.setZustand(simulator
				.getSimulationEntity(warteschlange).getZustand() - 1);
		// nur wenn noch jobs da sind, den nächsten Abgang ansetzen
		if (simulationEntity.getZustand() >= 1) {
			// Ist nur noch ein Job da, dann hat der seinen Job schon sebst
			// erzeugt
			IEreignis naechsterAbgang = new Abgang(
					simulator.getSimulationszeit() + (Math.random() / 2 + 0.75)
							* (1 / simulationEntity.getBedienrate()), debug,
					warteschlange);
			if (debug) {
				System.out.println("Naechster Abgang fuer Warteschlange "
						+ warteschlange + " bei "
						+ naechsterAbgang.getAusfuehrungszeit());

			}
			// nicht vergessen, das ereignis auch zur liste hinzuzufügen
			simulator.neuesEreignisIntegrieren(naechsterAbgang);
		}
		// Job in nächste Warteschlange hineinfügen.
		if (warteschlange + 1 < simulator.getNumberofSimulationEntitys()) {
			ISimulationEntity naechsteSimulationEntity = simulator
					.getSimulationEntity(warteschlange + 1);
			naechsteSimulationEntity.setZustand(naechsteSimulationEntity
					.getZustand() + 1);
			// Falls in der nächsten Warteschlange kein Job war, muss ein neuer
			// Abgang erzeugt werden
			if (naechsteSimulationEntity.getZustand() == 1) {
				Abgang abgang = new Abgang(simulator.getSimulationszeit()
						+ (Math.random() / 2 + 0.75)
						* (1 / naechsteSimulationEntity.getBedienrate()),
						debug, warteschlange + 1);
				simulator.neuesEreignisIntegrieren(abgang);
			}
		} else {
			simulator
					.setAbgefertigteObjekte(simulator.getAbgefertigteObjekte() + 1);
		}
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.luh.sim.java13.ue2.simulationmodel.IEreignis#getAusfuehrungszeit()
	 */
	public double getAusfuehrungszeit() {
		return ausfuehrungszeit;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(IEreignis o) {
		if (o.getAusfuehrungszeit() > getAusfuehrungszeit()) {
			return -1;
		} else if (o.getAusfuehrungszeit() < getAusfuehrungszeit()) {
			return 1;
		}
		return 0;

	}

}
