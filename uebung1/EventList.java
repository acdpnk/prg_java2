public class EventList implements IEventList {
	//note to self: this would have been way easier if i had implemented it as an array. there are never more than three elements in this "list". well.

	private EventListNode head;

	public void putAway (IEvent newEvent){
		if(hasContent()){
			putAway(head, newEvent);
			return;
		}
		head=new EventListNode(newEvent);
	}
	private void putAway(EventListNode node, IEvent newEvent){
		// we know head is not empty, otherwise this function would not have been called
		if (node.getEvent().getExecTime() >= newEvent.getExecTime()) {
			EventListNode displacedNode = node;
			node = new EventListNode(newEvent);
			node.setNext(displacedNode);
		}
		else if (node.getNext()==null) {
			node.setNext(new EventListNode(newEvent));
		}
		else{
			putAway(node.getNext(), newEvent);
		}
	}
	public IEvent nextEvent(){
		if (hasContent()) {
			return head.getEvent();
		}
		return null;
	}
	public void removeFirstEvent(){
		if (hasContent()) {
			head=head.getNext();
		}
	}
	public boolean hasContent(){
		return head != null ? true : false;
	}
}