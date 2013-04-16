package simulationmodel;
/**
 * a queue containing a number of waiting jobs (Warteschlange)
 */
public class Queue implements ISimulationEntity, Comparable<ISimulationEntity>{
	private int jobs;
	/**
	 * getter for the Queue's state (getZustand())
	 * @return number of waiting jobs
	 */
	public int getState(){
		return jobs;
	}

	/**
	 * setter for the Queue's state (setZustand())
	 * @param newState the new number of jobs in line
	 */
	public void setState(int newState){
		jobs=newState;
	}

	/**
	 * comparator method ordering queues by number of waiting jobs
	 * @param  entity the entity this queue is to be compared to
	 * @return        1 if this queue is longer than the other one, -1 if shorter, 0 if equal
	 */
	public int compareTo(ISimulationEntity entity){
		if(this.getState()>entity.getState()) return 1;
		else if(this.getState()<entity.getState()) return -1;
		else return 0;
	}
}