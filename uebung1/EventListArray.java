public class EventList implements IEventList {
	private IEvent[] events = new IEvent[1024];

	public void putAway (IEvent newEvent){
		int j = 0;
		IEvent[] temp = events;
		for(int i = j; i < events.length; i++){
			if(events[i] == null){
				events[i] = newEvent;
				j = i;
				break;
			}
			else if(newEvent.getExecTime() < events[i].getExecTime()){
				events[i] = newEvent;
				j = i;
				break;
			}
		}
		for(int i = j; i < temp.length - 1 && temp[i] != null; i++){
			events[i+1] = temp[i];
		}
	}
	public IEvent nextEvent(){
		return events[0];
	}
	public void removeFirstEvent(){
		IEvent[] temp = events;
		for(int i = 0; i < events.length - 1 && events[i] != null; i++){
			events[i] = temp[i+1];
		}
	}
	public boolean hasContent(){
		return events[0] != null ? true : false;
	}
}