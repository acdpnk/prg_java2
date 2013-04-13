package simulationmodel;
public class ArrivalEqualDist extends Arrival{
	public ArrivalEqualDist(simulatorcore.BasicSimulator simulator, double execTime, boolean verboseness){
		super((int)(Math.random()*simulator.getNumberOfEntities()), execTime, verboseness);
	}

	public void eventExec(simulatorcore.BasicSimulator simulator){
		if(verbose) System.out.println("\n\nArrival in Queue " + getQueueID() + " at " + getExecTime());
		simulator.getSimulationEntity(getQueueID()).setState(simulator.getSimulationEntity(getQueueID()).getState()+1);
		double arrivalTime = simulator.getCurrentSimTime() + (Math.random()*10) + 1;
		simulator.getEventList().putAway(new ArrivalEqualDist(simulator, arrivalTime, verbose));
		if(verbose) System.out.println("adding new Arrival to Queue " + getQueueID() + " at " + arrivalTime);
		if (simulator.getSimulationEntity(getQueueID()).getState() == 1) {  //should only occur when jobs was empty
			double departureTime = arrivalTime + (Math.random()*6 + 1);
			simulator.getEventList().putAway(new Departure(this.getQueueID(), departureTime, verbose));
			if(verbose) System.out.println("adding new Departure to Queue " + getQueueID() + " at " + departureTime);
		}
	}
}