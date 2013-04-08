public abstract class BasicSimulator {
	protected int currentSimTime;
	protected int currentQueueLength;
	protected int lastProbeTime;
	protected IEventList events;
	protected ISimulationEntity entity;

	void setCurrentSimTime(int time){
		currentSimTime = time;
	}
	int getCurrentSimTime(){
		return currentSimTime;
	}

	void setCurrentQueueLength(int length){
		currentQueueLength = length;
	}
	int getCurrentQueueLength(){
		return currentQueueLength;
	}

	void setLastProbeTime(int time){
		lastProbeTime = time;
	}
	int getLastProbeTime(){
		return lastProbeTime;
	}

	void setEventlist(IEventList list){
		events = list;
	}
	IEventList getEventlist(){
		return events;
	}

	void setSimulationEntity(ISimulationEntity entity){
		this.entity = entity;
	}
	ISimulationEntity getSimulationEntity(){
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