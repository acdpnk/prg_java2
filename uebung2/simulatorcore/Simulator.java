package simulatorcore;
import simulationmodel.*;
public class Simulator extends BasicSimulator {

	private boolean verbose;
	private double simulationEnd;
	private int iterations;
	private int mode; // 0=Equal Distribution; 1=Shortest

	public Simulator(int numberOfQueues, double end, boolean verboseness, int mode){
		super(numberOfQueues);
		simulationEnd = end;
		verbose = verboseness;
		this.mode=mode;
	}

	public Simulator(int numberOfQueues, double end){
		this(numberOfQueues, end, false, 0);
	}

	public double getSimulationEnd(){
		return simulationEnd;
	}

	public int getMode(){
		return mode;
	}

	public void init(){
		setCurrentSimTime(0);
		setLastProbeTime(0);
		setCompletedJobs(0);
		events = new EventListPQ();
		for(int i=0; i!=getNumberOfEntities(); i++){
			setCurrentQueueLength(i,0);
			setSimulationEntity(i, new Queue());
		}
		if(mode==0){
			events.putAway(new ArrivalEqualDist(this, getCurrentSimTime(), verbose));
			System.out.println("Mode: Equal Distribution");
		}
		if(mode==1){
			events.putAway(new ArrivalShortest(this, getCurrentSimTime(), verbose));
			System.out.println("Mode: Shortest");
		}
	}
	public void run(){
		while(events.hasContent() && events.nextEvent().getExecTime() < getSimulationEnd()){
			iterations++;
			IEvent event = events.nextEvent();
			events.removeFirstEvent();
			setCurrentSimTime(event.getExecTime());
			refreshQueueLength();
			event.eventExec(this);
			if(verbose){
				System.out.println("\nIteration:       " + iterations + "\nSimulation Time: " + getCurrentSimTime());
				for(int i=0; i<getNumberOfEntities(); i++) {
					System.out.println("\nQueue" + i +":" + "\nWaiting Jobs:    " + getSimulationEntity(i).getState() + "\nQueue Length:    " + (getCurrentQueueLength(i) / getCurrentSimTime()));
				}
			}
		}
		System.out.println("\n\nDone.\n\nTime: " + getCurrentSimTime() + "\nCompleted Jobs: " + getCompletedJobs() + "\n\nAverage Queue Lengths over whole simulation: ");
		for (int i=0; i<getNumberOfEntities(); i++) {
		 	System.out.println("Queue" + i + ": \t" + (getCurrentQueueLength(i) / getCurrentSimTime()));
		 }
	}

}