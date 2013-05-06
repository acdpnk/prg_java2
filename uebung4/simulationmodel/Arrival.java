package simulationmodel;
/**
 * an arrival to a queue. for single queue simulations. (Ankunft)
 */
public class Arrival extends Event {
	/**
	 *
	 */
	private double execTime;

	/**
	 * constructor for class Arrival (this constructor for backwards compatibility and derived classes only)
	 * @param  queueID     id of the queue the arrival belongs to
	 * @param  execTime    the time the arrival takes effect
	 * @param  verboseness prints debug info if true
	 */
	public Arrival(int queueID, double execTime, boolean verboseness){
		super(queueID, verboseness);
		this.execTime = execTime;
	}

	/**
	 * standard constructor for class Arrival
	 * @param  execTime    the time the arrival takes effect
	 * @param  verboseness prints debug info if true
	 */
	public Arrival(double execTime, boolean verboseness){
		this(0, execTime, verboseness);
	}

	/**
	 * executes the arrival
	 * @param simulator the simulator to be affected
	 */
	public void eventExec(simulatorcore.BasicSimulator simulator){
		simulator.removeFirstEvent();
		if(verbose) System.out.println("\n\nArrival in Queue " + getQueueID() + " at " + getExecTime());
		simulator.getSimulationEntity(getQueueID()).setState(simulator.getSimulationEntity(getQueueID()).getState()+1);
		double arrivalTime = simulator.getCurrentSimTime() + (Math.random()*9) + 1;
		simulator.getEventList().putAway(new Arrival(arrivalTime, verbose));
		if(verbose) System.out.println("adding new Arrival to Queue " + getQueueID() + " at " + arrivalTime);
		if (simulator.getSimulationEntity(getQueueID()).getState() == 1) {  //should only occur when jobs was empty
			double departureTime = simulator.getCurrentSimTime() + (Math.random()*5 + 1);
			simulator.getEventList().putAway(new Departure(departureTime, verbose));
			if(verbose) System.out.println("adding new Departure to Queue at " + departureTime);
		}
	}

	/**
	 * getter method for the execution time
	 * @return the execution time
	 */
	public double getExecTime(){
		return execTime;
	}

}