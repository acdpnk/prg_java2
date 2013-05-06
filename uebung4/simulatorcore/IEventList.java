package simulatorcore;
import simulationmodel.*;
/**
 * interface for the object containing the list of events that are to affect the simulation. should be sorted by execution time. (IEreignisListe)
 */
public interface IEventList{
	/**
	 * add a new IEvent to the list. the IEvent should be sorted in according to its exeution time (ereignisEinsortieren())
	 * @param newEvent the IEvent to be put away
	 */
	void putAway (IEvent newEvent);
	/**
	 * request the next event on the list (naechstesEreignis())
	 * @return the next event on the list, or null if empty
	 */
	IEvent nextEvent();
	/**
	 * delete the first event on the list (erstesEreignisEntfernen())
	 */
	void removeFirstEvent();
	/**
	 * check if there are events on the list or if the list is empty (hatNochInhalt())
	 * @return true if events are present, false if empty
	 */
	boolean hasContent();
}