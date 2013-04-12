package simulatorcore;
import simulationmodel.*;
public abstract class BasicSimulator {
	protected int currentSimTime;
	protected int currentQueueLength;
	protected int lastProbeTime;
	protected IEventList events;
	protected ISimulationEntity entity;

	void setCurrentSimTime(int time){
		currentSimTime = time;
	}
	public int getCurrentSimTime(){
		return currentSimTime;
	}

	void setCurrentQueueLength(int length){
		currentQueueLength = length;
	}
	public int getCurrentQueueLength(){
		return currentQueueLength;
	}

	void setLastProbeTime(int time){
		lastProbeTime = time;
	}
	public int getLastProbeTime(){
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
		int time = getCurrentSimTime() - getLastProbeTime();
		currentQueueLength += entity.getState()*time;
		setLastProbeTime(getCurrentSimTime());
	}

	abstract void init();
	abstract void run();

}