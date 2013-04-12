package simulationmodel;
public abstract class Event implements IEvent, Comparable<IEvent>{
	protected boolean verbose;

	public Event(){
		this(false);
	}

	public Event(boolean verboseness){
		verbose = verboseness;
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