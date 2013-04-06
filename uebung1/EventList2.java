public class EventList implements IEventList {
	private EventListNode head = new EventListNode();

	public void putAway (IEvent newEvent){
		putAway(newEvent, head);
	};
	private void putAway(IEvent newEvent, EventListNode node){
		if (node.getEvent() == null) {
			node.setEvent(newEvent);
			return;
		}
		if (node.getNext() == null) {
			node.setNext(new EventListNode());
		}
		putAway(newEvent, node.getNext());
	}
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