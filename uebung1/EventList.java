public class EventList implements IEventList {
	private EventListNode head = new EventListNode();
	private EventListNode tail = head;

	public void putAway (IEvent newEvent){
		if (tail.getEvent() == null) {
			tail.setEvent(newEvent);
		}
		else {
			tail.setNext(new EventListNode);
			tail.next.setEvent(newEvent);
		}
	};
	public IEvent nextEvent(){
		if (head.getEvent() != null) {
			return head.getEvent();
		}
		return null;
	};
	public void removeFirstEvent(){
		if (head.getNext() != null) {
			head = head.getNext();
		} else{
			head = new EventListNode();
		}
	};
	public boolean hasContent(){
		return head.getEvent() != null ? true : false;
	};
}