package simulationmodel;
/**
 * a departure (D: abgang)
 */
public class Departure extends Event {
	/**
	 * the time the departure is executed
	 */
	private double execTime;

	/**
	 * constructor for a departure
	 * @param  queueID     the queue the departure should take place in
	 * @param  execTime    the departures execution time
	 * @param  verboseness prints debug information if true
	 */
	protected Departure(int queueID, double execTime, boolean verboseness){
		super(queueID, verboseness);
		this.execTime = execTime;
	}

	/**
	 * optional constructor, needed for backwards compatibility with Arrival
	 * @param  execTime    execution time
	 * @param  verboseness prints debug information if true
	 */
	protected Departure(double execTime, boolean verboseness){
		this(0, execTime, verboseness);
	}

	/**
	 * executes the departure
	 * @param simulator the simulator the departure should affect
	 */
	public void eventExec(simulatorcore.BasicSimulator simulator){
		if(verbose) System.out.println("\n\nDeparture from Queue " + getQueueID() + " at " + getExecTime());
		if(simulator.getSimulationEntity(getQueueID()).getState() > 1){
			double departureTime = simulator.getCurrentSimTime() + (Math.random()*6) + 1;
			simulator.getEventList().putAway(new Departure(getQueueID(), departureTime, verbose));
			if(verbose) System.out.println("adding new Departure to Queue " + getQueueID() + " at " + departureTime);
		}
		simulator.getSimulationEntity(getQueueID()).setState(simulator.getSimulationEntity(getQueueID()).getState()-1);
		simulator.setCompletedJobs(simulator.getCompletedJobs()+1);
	}

	/**
	 * getter for execution time
	 * @return execution time
	 */
	public double getExecTime(){
		return execTime;
	}
}