package de.luh.sim.java13.ue3.simulationmodel;

import de.luh.sim.java13.ue3.simulatorcore.BasicSimulator;

/**
 * Interface für ein Ereignis.
 * Minimale Anforderungen an ein Ereignis: Hat eine
 * bestimmte Ausführungszeit, diese kann gesetzt und
 * ausgelesen werden. Weiterhin muss das Ereignis
 * ausgeführt werden können.
 * 
 * @author Matthias Becker
 */
public interface IEreignis extends Comparable<IEreignis>{
	
	/**
	 * Führt das Ereignis im angegebenen Simulator aus.
	 * 
	 * @param simulator Ziel-Simulator.
	 */
	public void ereignisAusfuehren(BasicSimulator simulator);
		
	/**
	 * Liefert den Ausführungszeitpunkt für
	 * dieses Ereignis.
	 * 
	 * @return Ausf�hrungszeitpunkt
	 */
	public double getAusfuehrungszeit();

}