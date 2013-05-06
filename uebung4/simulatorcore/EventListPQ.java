package simulatorcore;
import java.util.PriorityQueue;
import simulationmodel.*;

/**
 * EventList implementation using a PriorityQueue. (EreignisListePQ)
 */
public class EventListPQ implements IEventList{
	private PriorityQueue<IEvent> events = new PriorityQueue<IEvent>();

	/**
	 * put away a new IEvent. (ereignisEinsortieren())
	 * @param newEvent the IEvent to be put away
	 */
	public void putAway (IEvent newEvent){
		events.add(newEvent);
	}

	/**
	 * request the next IEvent on the list (naechstesEreignis())
	 * @return the next IEvent on the list
	 */
	public IEvent nextEvent(){
		return events.peek();
	}

	/**
	 * deletes the IEvent currently on top of the list (erstesEreignisEntfernen())
	 */
	public void removeFirstEvent(){
		events.remove(events.peek());
	}

	/**
	 * check whether there's still content on the list (hatNochInhalt())
	 * @return true if there are IEvents on the list, false if list is empty
	 */
	public boolean hasContent(){
		return events.peek() != null ? true : false;
	}
}