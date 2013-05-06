package de.luh.sim.java13.ue3.simulationmodel;
/**
 * Dies ist ein Interface für alle Entitäten, 
 * die in einer Simulation vorkommen können.
 * (z.B. eine Warteschlange ...)
 * 
 * @author Matthias Becker
 */
public interface ISimulationEntity {

	/**
	 * Wie auch immer es intern aussieht, gib uns doch bitte
	 * einen int Wert als Zustandsbeschreibung zurück.
	 * (z.B. Anzahl Jobs in einer Warteschlange ...)
	 * 
	 * @return Zustandscode
	 */
	public int getZustand();
	
	/**
	 * Den Zustand soll man natuerlich auch ändern koennen. 
	 * (z.B. wenn ein Job dazu kommt -> Zustand++ ...)
	 * 
	 * @param neuerZustand Code des neuen Zustands, den das Simulationselement annimt
	 */
	public void setZustand(int neuerZustand);
	
	
	/**
	 * Gibt die Bedienrate der Warteschlange zurück. 
	 * @return Bedienrate
	 */
	public double getBedienrate();
	
	/**
	 * Gibt den Namen der Warteschlange zurück. 
	 * @return Name der Warteschlange
	 */
	public String getName();
}
