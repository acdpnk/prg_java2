package simulatorcore;
import simulationmodel.*;

public abstract class BasicSimulator {
	protected double currentSimTime;
	protected double lastProbeTime;
	protected IEventList events;
	protected int completedJobs;

	protected int numberOfEntities;
	protected double[] currentQueueLength;
	protected ISimulationEntity[] entity;

	protected BasicSimulator(int numberOfEntities){
		this.numberOfEntities = numberOfEntities;
		currentQueueLength = new double[numberOfEntities];
		entity = new ISimulationEntity[numberOfEntities];
	}

	public int getNumberOfEntities(){
		return numberOfEntities;
	}

	public int getShortestEntityID(){
		int shortestEntityID=0;
		for (int i=0; i<entity.length; i++) {
			if(getSimulationEntity(shortestEntityID).compareTo(getSimulationEntity(i))>0) shortestEntityID=i;
		}
		return shortestEntityID;
	}

	void setCurrentSimTime(double time){
		currentSimTime = time;
	}
	public double getCurrentSimTime(){
		return currentSimTime;
	}

	void setCurrentQueueLength(int entityNumber, double length){
		currentQueueLength[entityNumber] = length;
	}
	public double getCurrentQueueLength(int entityNumber){
		return currentQueueLength[entityNumber];
	}

	void setLastProbeTime(double time){
		lastProbeTime = time;
	}
	public double getLastProbeTime(){
		return lastProbeTime;
	}

	void setEventList(IEventList list){
		events = list;
	}
	public IEventList getEventList(){
		return events;
	}

	void setSimulationEntity(int entityNumber, ISimulationEntity entity){
		this.entity[entityNumber] = entity;
	}
	public ISimulationEntity getSimulationEntity(int entityNumber){
		return this.entity[entityNumber];
	}

	void removeFirstEvent(){
		events.removeFirstEvent();
	}

	void putInNewEvent(IEvent event){
		events.putAway(event);
	}

	void refreshQueueLength(){
		double time = getCurrentSimTime() - getLastProbeTime();
		for (int i=0; i<getNumberOfEntities(); i++){
			currentQueueLength[i] += entity[i].getState()*time;
		}
		setLastProbeTime(getCurrentSimTime());
	}

	public void setCompletedJobs(int jobs){
		this.completedJobs=jobs;
	}
	public int getCompletedJobs(){
		return this.completedJobs;
	}

	abstract void init();
	abstract void run();

}