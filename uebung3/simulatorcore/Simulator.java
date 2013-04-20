package simulatorcore;
import simulationmodel.*;
/**
 * a simulator
 */
public class Simulator extends BasicSimulator {
	/**
	 * number of queues to be simulated
	 */
	private int numberOfQueues;
	/**
	 * print debug information if true (is passed on to all other methods and classes employed)
	 */
	private boolean verbose;
	/**
	 * time at which the simulation should end
	 */
	private double simulationEnd;
	/**
	 * number of cycles the simulation has run
	 */
	private int iterations;
	/**
	 * simulation mode.<br>
	 * 0 means new Arrivals are distributed randomly between queues.<br>
	 * 1 means new Arrivals are always added to the shortest queue present, or the one with the lowest id in case of a tie.
	 */
	private int mode; // 0=Equal Distribution; 1=Shortest

	/**
	 * constructor for the simulator
	 * @param  numberOfQueues the number of queues to simulate
	 * @param  end            the end time of the simulation
	 * @param  verboseness    prints additional debug info if true
	 * @param  mode           0: Arrivals are distributed randomly, 1: Arrivals are added to shortest queue
	 */
	public Simulator(int numberOfQueues, double end, boolean verboseness, int mode){
		//super(numberOfQueues);
		this.numberOfQueues = numberOfQueues;
		simulationEnd = end;
		verbose = verboseness;
		this.mode=mode;
	}

	/**
	 * getter for the simulation endpoint
	 * @return the simulations max runtime
	 */
	public double getSimulationEnd(){
		return simulationEnd;
	}

	/**
	 * getter for the simulation's mode
	 * @return the simulation's mode. 0: equal distribution, 1: shortest
	 */
	public int getMode(){
		return mode;
	}

	/**
	 * setter vor verboseness
	 * @param verboseness prints debug info if true
	 */
	protected void setVerboseness(boolean verboseness){
		verbose = verboseness;
	}

	/**
	 * initialize the simulator
	 */
	public void init(){
		setCurrentSimTime(0);
		setLastProbeTime(0);
		setCompletedJobs(0);
		events = new EventListPQ();
		for(int i=0; i!=numberOfQueues; i++){
			addSimulationEntity(new Queue());
			currentQueueLength.add(0.0);
		}

		assert getNumberOfEntities() > 0;

		if(mode==0){
			events.putAway(new ArrivalEqualDist(this, Math.random()*9+1, verbose));
			System.out.println("Mode: Equal Distribution");
		}
		if(mode==1){
			events.putAway(new ArrivalShortest(this, Math.random()*9+1, verbose));
			System.out.println("Mode: Shortest");
		}
	}

	/**
	 * run the simulation
	 */
	public void run(){
		IEvent event = events.nextEvent();
		while(event != null && getCurrentSimTime() < getSimulationEnd()){
			iterations++;
			setCurrentSimTime(event.getExecTime());
			refreshQueueLength();
			event.eventExec(this);
			event = events.nextEvent();

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