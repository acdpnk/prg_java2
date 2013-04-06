public abstract class BasicSimulator {
	protected double currentSimTime;
	protected double currentQueueLength = 0;
	protected double lastProbeTime;
	protected IEventList events;
	protected ISimulationEntity entity;

	void setCurrentSimTime(double time){
		currentSimTime = time;
	}
	double getCurrentSimTime(){
		return currentSimTime;
	}

	void setLastProbeTime(double time){
		lastProbeTime = time;
	}
	double getLastProbeTime(){
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
		double time = getCurrentSimTime() - getLastProbeTime();
		currentQueueLength += entity.getState()*time;
		setLastProbeTime(getCurrentSimTime());
	}

	abstract void init();
	abstract void run();

}