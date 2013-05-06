package de.luh.sim.java13.ue3.simulatorcore;

import de.luh.sim.java13.ue3.simulationmodel.IEreignis;

/**
 * Interface für eine beliebige Datenstruktur,
 * die die Ereignisse in einer Simulation geordnet
 * aufbewahrt. Weiterhin muss sie das nächste
 * abzuarbeitende Ereignis zuückgeben können.
 * 
 * @author Matthias Becker
 */
public interface IEreignisliste {

	/**
	 * Neues Ereignis in die schon vorhandenen zeitlich
	 * einsortieren.
	 * 
	 * @param neuesEreignis einzusortierendes Ereignis
	 */
	public void einsortieren (IEreignis neuesEreignis);
	
	/**
	 * Gibt das nächste abzuarbeitende Ereignis zurueck.
	 * 
	 * @return zeitlich nächstes abzuarbeitendes Ereignis
	 */
	public IEreignis naechstesEreignis();
	
	/**
	 * Entfernt erstes Ereignis aus der Datenstruktur.
	 * (z.B. wenn das erste Ereignis geholt und abgearbeitet
	 * wurde, dann sollte man es auch entfernen.
	 */
	public void erstesEreignisEntfernen();
	
	/**
	 * Gibt es noch Ereignisse in der Liste?
	 * 
	 * @return true falls noch Ereignisse in Liste sind
	 */
	public boolean hatNochInhalt();
	
}
