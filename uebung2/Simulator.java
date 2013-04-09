public class Simulator extends BasicSimulator {

	private int simulationEnd;
	private int iterations;

	public Simulator(int end){
		simulationEnd = end;
	}

	public int getSimulationEnd(){
		return simulationEnd;
	}

	public void init(){
		setCurrentSimTime(0);
		setCurrentQueueLength(0);
		setLastProbeTime(0);
		setSimulationEntity(new Queue());
		events = new EventListPQ();
		events.putAway(new Arrival(0));
	}
	public void run(){
		while(events.hasContent() && events.nextEvent().getExecTime() < getSimulationEnd()){
			iterations++;
			IEvent event = events.nextEvent();
			events.removeFirstEvent();
			setCurrentSimTime(event.getExecTime());
			refreshQueueLength();
			event.eventExec(this);
			System.out.println("Iteration:       " + iterations + "\nSimulation Time: " + getCurrentSimTime() + "\nWaiting Jobs:    " + getSimulationEntity().getState() + "\nQueue Length:    " + ((double)getCurrentQueueLength() / (double)getCurrentSimTime()) + "\n");
		}
		System.out.println("Average Queue Length over whole simulation: " + ((double)getCurrentQueueLength() / (double)getCurrentSimTime()));
	}

}