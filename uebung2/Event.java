public abstract class Event implements IEvent, Comparable<IEvent>{
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