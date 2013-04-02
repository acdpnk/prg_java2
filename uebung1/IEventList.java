public interface IEventList{
	void putAway (IEvent newEvent);
	IEvent next();
	void removeFirstEvent();
	boolean hasContent();
}