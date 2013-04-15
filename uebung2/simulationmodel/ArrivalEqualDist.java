package simulationmodel;
/**
 * Arrival for multi queue simulations. Arrivals are distributed randomly between queues. (AnkunftGleichVerteilt)
 */
public class ArrivalEqualDist extends Arrival{
	/**
	 * constructor for ArrivalEqualDist
	 * @param  simulator   the simulator to be affected (used to determine the number of queues present)
	 * @param  execTime    the Arrivals execution time
	 * @param  verboseness prints debug info if true
	 */
	public ArrivalEqualDist(simulatorcore.BasicSimulator simulator, double execTime, boolean verboseness){
		super((int)(Math.random()*simulator.getNumberOfEntities()), execTime, verboseness);
	}

	public void eventExec(simulatorcore.BasicSimulator simulator){
		simulator.removeFirstEvent();
		
		if(verbose) System.out.println("\n\nArrival in Queue " + getQueueID() + " at " + getExecTime());
		
		double arrivalTime = simulator.getCurrentSimTime() + (Math.random()*9) + 1;

		simulator.getEventList().putAway(new ArrivalEqualDist(simulator, arrivalTime, verbose));

		if(verbose) System.out.println("adding new Arrival to Queue " + getQueueID() + " at " + arrivalTime);

		simulator.getSimulationEntity(getQueueID()).setState(simulator.getSimulationEntity(getQueueID()).getState()+1);

		if (simulator.getSimulationEntity(getQueueID()).getState() == 1) {  //should only occur when jobs was empty
			double departureTime = simulator.getCurrentSimTime() + (Math.random()*5 + 1);
			simulator.getEventList().putAway(new Departure(this.getQueueID(), departureTime, verbose));
			if(verbose) System.out.println("adding new Departure to Queue " + getQueueID() + " at " + departureTime);
		}
	}
}