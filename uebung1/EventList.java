public class EventList implements IEventList {
	private EventListNode head;

	public void putAway (IEvent newEvent){
		if(hasContent()){
			putAway(head, newEvent);
			return;
		}
		head=new EventListNode();	//might want to change this to
		head.setEvent(newEvent);	//head=new EventListNode(newEvent);
	}
	private void putAway(EventListNode node, IEvent newEvent){
		System.out.println("foo");
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