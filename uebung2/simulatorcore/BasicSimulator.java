package simulatorcore;
import simulationmodel.*;
public abstract class BasicSimulator {
	protected double currentSimTime;
	protected double currentQueueLength;
	protected double lastProbeTime;
	protected IEventList events;
	protected ISimulationEntity entity;

	void setCurrentSimTime(double time){
		currentSimTime = time;
	}
	public double getCurrentSimTime(){
		return currentSimTime;
	}

	void setCurrentQueueLength(double length){
		currentQueueLength = length;
	}
	public double getCurrentQueueLength(){
		return currentQueueLength;
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

	void setSimulationEntity(ISimulationEntity entity){
		this.entity = entity;
	}
	public ISimulationEntity getSimulationEntity(){
		return this.entity;
	}

	void removeFirstEvent(){
		events.removeFirstEvent();
	}

	void putInNewEvent(IEvent event){
		events.putAway(event);
	}

	void refreshQueueLength(){
		double time = getCurrentSimTime() - getLastProbeTime();
		currentQueueLength += entity.getState()*time;
		setLastProbeTime(getCurrentSimTime());
	}

	abstract void init();
	abstract void run();

}