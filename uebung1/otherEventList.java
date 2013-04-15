

import java.util.ArrayList;
import java.util.List;

public class EventList implements IEventList {
	
	private List<IEvent> list;
	
	public EventList() {
		list = new ArrayList<IEvent>();
	}

	public void putAway(IEvent newEvent) {
		if (list.isEmpty()) { // Sonderfall
			list.add(0,newEvent);
			return;
		}
		int i=0;
		for (IEvent o : list) {
			if (newEvent.getExecTime()<o.getExecTime()) {
				list.add(i, newEvent);
				return;
			}
			i++;
		}
		list.add(newEvent);
	}

	public void removeFirstEvent() {
		list.remove(0);
	}

	public boolean hasContent() {
		return !list.isEmpty();
	}

	public IEvent nextEvent() {
		return hasContent() ? list.get(0) : null;
	}

}
