/**
 * Dieses Package enthält abstrakte Klassen und Interfaces, die für die
 * Implementierung verschiedenster Simulationen benötigt werden.
 */
package de.luh.sim.java13.ue3.simulatorcore;

import java.util.ArrayList;
import java.util.List;

import de.luh.sim.java13.ue3.simulationmodel.IEreignis;
import de.luh.sim.java13.ue3.simulationmodel.ISimulationEntity;

/**
 * Stellt Grundelemente eines Ereignis-diskreten Simulators zur Verfügung.
 * 
 * @author Mattias Becker
 */
public abstract class BasicSimulator {

	/**
	 * Jeder Simulator braucht eine Simulationszeit.
	 */
	protected double simulationszeit = 0.0; // selbsterklaerend

	protected List<Double> summeWarteschlangenlaenge;

	protected double letzteAktualisierung;
	protected int abgefertigteObjekte;

	/**
	 * Jeder Simulator braucht eine Ereignisliste. meineEreignisliste muss mit
	 * einer Klasse initialisiert werden, die das Interface IEreignisliste
	 * implementiert. Das ist das Interface für eine Datenstruktur, die die
	 * Ereignisse aufnimmt.
	 * 
	 * @see IEreignisliste
	 */
	protected IEreignisliste ereignisliste;

	/**
	 * Jeder Simulator braucht ein verarbeitetes Entity. meinSimulationsEntity
	 * muss mit einer Klasse initialisiert werden, die das Interface
	 * ISimulationEntity implementiert. Das ist das Interface für die Entität,
	 * die in der Simulation verarbeitet wird.
	 * 
	 * @see ISimulationEntity
	 */
	protected List<ISimulationEntity> simulationsEntity;

	public BasicSimulator() {
		summeWarteschlangenlaenge = new ArrayList<Double>();
		simulationsEntity = new ArrayList<ISimulationEntity>();
	}

	/**
	 * Fügt ein neues ISimulationEntity der Simulation hinzu. Z.B. eine
	 * Warteschlange.
	 * 
	 * @param entity
	 *            einzufügendes ISimulationEntity
	 */
	public void addSimulationEntity(ISimulationEntity entity) {
		summeWarteschlangenlaenge.add(0.0);
		simulationsEntity.add(entity);
	}

	/**
	 * Aktualisiert die Warteschlangenlänge der Warteschlangen.
	 */
	public void aktualisiereWarteschlangenlaenge() {
		for (int i = 0; i < simulationsEntity.size(); i++) {
			summeWarteschlangenlaenge.set(i, summeWarteschlangenlaenge.get(i)
					+ (simulationszeit - letzteAktualisierung)
					* simulationsEntity.get(i).getZustand());
		}
		if (summeWarteschlangenlaenge.get(0) < 0) {
			System.out.println("Fehler");
		}
		letzteAktualisierung = simulationszeit;
	}

	/**
	 * Erstes Ereignis in der Ereignisliste entfernen. Vor dem Entfernen sollte
	 * das Ereignis inspiziert und ausgefuehrt werden.
	 */
	public void erstesEreignisEntfernen() {
		ereignisliste.erstesEreignisEntfernen();

	}

	/**
	 * Gibt die Anzahl der abgefertigten Objekte zurück.
	 * 
	 * @return Anzahl der abgefertigten Objekte
	 */
	public int getAbgefertigteObjekte() {
		return abgefertigteObjekte;
	}

	/**
	 * Gibt die Anzahl der in der Simulation befindlichen ISimulationEntitys
	 * zurück.
	 * 
	 * @return Anzahl der ISimulationEntitys
	 */
	public int getNumberofSimulationEntitys() {
		return simulationsEntity.size();
	}

	/**
	 * Gibt die Simulationsentität zur�ck.
	 * 
	 * @return Die Entität der Simulation.
	 */
	public ISimulationEntity getSimulationEntity(int id) {
		return this.simulationsEntity.get(id);
	};

	/**
	 * Getter fuer die Simulationszeit.
	 * 
	 * @return Stand der Simulationszeit.
	 */
	public double getSimulationszeit() {
		return simulationszeit;
	}

	/**
	 * Initialisierung der Simulation. Es muss hier mindestens ein Startereignis
	 * festgelegt werden, das dann evtl. weitere Ereignisse erzeugt.
	 */
	abstract public void init();

	/**
	 * Ein neues Ereignis muss von der Simulation verarbeitet werden. Dazu muss
	 * es zunaechst einmal in die Ereignisliste einsortiert werden.
	 * 
	 * @param ereignis
	 *            neues Ereignis, das in der Simulation gespeichert werden soll
	 */
	public void neuesEreignisIntegrieren(IEreignis ereignis) {
		ereignisliste.einsortieren(ereignis);
	}

	/**
	 * Startet den eigentlichen Simulationsalgorithmus. (Nimm erstes Ereignis
	 * aus der Liste, führe es aus, sortiere eventuell neu erzeugte Ereignisse
	 * ein usw.)
	 */
	abstract public void run();

	public void setAbgefertigteObjekte(int abgefertigteObjekte) {
		this.abgefertigteObjekte = abgefertigteObjekte;
	}
}
