public interface IEventList{
	void putAway (IEvent newEvent);
	IEvent nextEvent();
	void removeFirstEvent();
	boolean hasContent();
}