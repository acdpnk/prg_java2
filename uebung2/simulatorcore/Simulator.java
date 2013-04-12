package simulatorcore;
import simulationmodel.*;
public class Simulator extends BasicSimulator {

	private boolean verbose;
	private double simulationEnd;
	private int iterations;

	public Simulator(int numberOfQueues, double end, boolean verboseness){
		super(numberOfQueues);
		simulationEnd = end;
		verbose = verboseness;
	}

	public Simulator(int numberOfQueues, double end){
		this(numberOfQueues, end, false);
	}

	public double getSimulationEnd(){
		return simulationEnd;
	}

	public void init(){
		setCurrentSimTime(0);
		setLastProbeTime(0);
		events = new EventListPQ();
		events.putAway(new Arrival(0, verbose));
		for(int i=0; i!=getNumberOfEntities(); i++){
			setCurrentQueueLength(i,0);
			setSimulationEntity(i, new Queue());
		}
	}
	public void run(){
		while(events.hasContent() && events.nextEvent().getExecTime() < getSimulationEnd()){
			iterations++;
			IEvent event = events.nextEvent();
			events.removeFirstEvent();
			setCurrentSimTime(event.getExecTime());
			for(int i=0; i<getNumberOfEntities(); i++){
				refreshQueueLength(i);
			}
			event.eventExec(this);
			if(verbose){
				System.out.println("\nIteration:       " + iterations + "\nSimulation Time: " + getCurrentSimTime());
				for(int i=0; i<getNumberOfEntities(); i++) {
					System.out.println("\nQueue" + i +":" + "\nWaiting Jobs:    " + getSimulationEntity(i).getState() + "\nQueue Length:    " + (getCurrentQueueLength(i) / getCurrentSimTime()));
				}
			}
		}
		System.out.println("\n\nDone.\nAverage Queue Lengths over whole simulation: ");
		for (int i=0; i<getNumberOfEntities(); i++) {
		 	System.out.println("Queue" + i + ": " + (getCurrentQueueLength(0) / getCurrentSimTime()));
		 }
	}

}