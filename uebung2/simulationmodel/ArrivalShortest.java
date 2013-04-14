package simulationmodel;
/**
 * Arrival for multi queue simulations. Arrivals are added to shortest queue present at time of generation. if multiple queues of equal length are present, the Arrival is added to the queue with the lowest id.
 */
public class ArrivalShortest extends Arrival{
	/**
	 * constructor for ArrivalShortest
	 * @param  simulator   the simulator to be affected (used to determine the shortest queue present)
	 * @param  execTime    the arrivals execution time
	 * @param  verboseness prints debug info if true
	 */
	public ArrivalShortest(simulatorcore.BasicSimulator simulator, double execTime, boolean verboseness){
		super(simulator.getShortestEntityID(), execTime, verboseness);
	}

	/**
	 * this method executes the arrival
	 * @param simulator the simulator to be affected
	 */
	public void eventExec(simulatorcore.BasicSimulator simulator){
		if(verbose) System.out.println("\n\nArrival in Queue " + getQueueID() + " at " + getExecTime());
		simulator.getSimulationEntity(getQueueID()).setState(simulator.getSimulationEntity(getQueueID()).getState()+1);
		double arrivalTime = simulator.getCurrentSimTime() + (Math.random()*10) + 1;
		if(verbose) System.out.println("adding new Arrival to Queue " + simulator.getShortestEntityID() + " at " + arrivalTime);
		simulator.getEventList().putAway(new ArrivalShortest(simulator, arrivalTime, verbose));
		if (simulator.getSimulationEntity(getQueueID()).getState() == 1) {  //should only occur when jobs was empty
			double departureTime = arrivalTime + (Math.random()*6 + 1);
			simulator.getEventList().putAway(new Departure(this.getQueueID(), departureTime, verbose));
			if(verbose) System.out.println("adding new Departure to Queue " + getQueueID() + " at " + departureTime);
		}
	}
}