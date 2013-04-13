package simulationmodel;
public abstract class Event implements IEvent, Comparable<IEvent>{
	protected boolean verbose;
	protected int queueID;

	public Event(int queueID, boolean verboseness){
		this.queueID=queueID;
		this.verbose=verboseness;
	}

	public Event(boolean verboseness){
		this(0, verboseness);
	}

	public Event(){
		this(false);
	}

	public int getQueueID(){
		return this.queueID;
	}

	protected void setQueueID(int queueID){
		this.queueID=queueID;
	}

	public int compareTo(IEvent event){
		if (this.getExecTime()>event.getExecTime()) {
			return 1;
		}
		else if (this.getExecTime()<event.getExecTime()) {
			return -1;
		}
		return 0;
	}
}