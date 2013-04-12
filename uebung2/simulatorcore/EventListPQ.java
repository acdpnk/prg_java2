package simulatorcore;
import java.util.PriorityQueue;
import simulationmodel.*;

public class EventListPQ implements IEventList{
private PriorityQueue<IEvent> events = new PriorityQueue<IEvent>();

	public void putAway (IEvent newEvent){
		events.add(newEvent);
	}
	public IEvent nextEvent(){
		return events.peek();
	}
	public void removeFirstEvent(){
		events.remove(events.peek());
	}
	public boolean hasContent(){
		return events.peek() != null ? true : false;
	}
}