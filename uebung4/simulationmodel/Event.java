package simulationmodel;
/**
 * abstract superclass for events.
 */
public abstract class Event implements IEvent, Comparable<IEvent>{
	/**
	 * prints debug information if true.
	 */
	protected boolean verbose;

	/**
	 * queue the event belongs to.
	 */
	protected int queueID;

	protected double execTime;

	/**
	 * an event.
	 * @param  queueID     queue the event belongs to.
	 * @param  verboseness prints debog information if true.
	 */
	public Event(int queueID, double execTime, boolean verboseness){
		this.queueID=queueID;
		this.execTime=execTime;
		this.verbose=verboseness;
	}

	/**
	 * getter for queue id.
	 * @return queue id
	 */
	public int getQueueID(){
		return this.queueID;
	}

	/**
	 * setter for queue id
	 * @param queueID queue id
	 */
	protected void setQueueID(int queueID){
		this.queueID=queueID;
	}

	public double getExecTime(){
		return execTime;
	}

	/**
	 * events are comparable by their execution time.
	 * @param  event the other event to compare this event to
	 * @return       1 if this event is executed later than the other event, -1 if this event is executed earlier than the other event, 0 if tied.
	 */
	public int compareTo(IEvent event){
		if (this.getExecTime()>event.getExecTime()) {
			return 1;
		}
		else if (this.getExecTime()<event.getExecTime()) {
			return -1;
		}
		return 0;
	}
	public static double jitter(double time) {
	return time*(Math.random()*0.5 + 0.75);
	}
}