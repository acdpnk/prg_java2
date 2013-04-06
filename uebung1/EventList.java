public class EventList implements IEventList {
	private EventListNode root = new EventListNode();

	public void putAway (IEvent newEvent){
		putAway(newEvent, root);
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
		if (root.getEvent() != null) {
			return root.getEvent();
		}
		return null;
	};
	public void removeFirstEvent(){
		if (root.getNext() != null) {
			root = root.getNext();
		} else{
			root = new EventListNode();
		}
	};
	public boolean hasContent(){
		return root.getEvent() != null ? true : false;
	};
}