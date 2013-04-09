public class EventListNode {
	private IEvent event;
	private EventListNode next;

	public EventListNode(IEvent event){
		this.event = event;
	}

	public IEvent getEvent(){
		return event;
	}
	// public void setEvent(IEvent event){
	// 	this.event = event;
	// }

	public EventListNode getNext(){
		return next;
	}
	public void setNext(EventListNode node){
		next = node;
	}
	// public void setNextEvent(IEvent event){
	// 	next.setEvent(event);
	// }
}