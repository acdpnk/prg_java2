package simulationmodel;

public class ArrivalSeq extends Event{


	public ArrivalSeq(double execTime, boolean verboseness){
		super(0, execTime, verboseness);
	}
	public void eventExec(simulatorcore.BasicSimulator simulator){
		if(simulator instanceof simulatorcore.SimulatorSeq){
			eventExec(simulator);
		}
	}
	public void eventExec(simulatorcore.SimulatorSeq simulator){
		simulator.removeFirstEvent();
		if(verbose) System.out.println("\n\nArrival in Queue " + simulator.getSimulationEntity(getQueueID()).getName() + " (" + getQueueID() + ") at " + getExecTime());
		simulator.getSimulationEntity(getQueueID()).setState(simulator.getSimulationEntity(getQueueID()).getState()+1);
		double arrivalTime = simulator.getCurrentSimTime() + jitter(1/simulator.getArrivalRate());
		simulator.getEventList().putAway(new ArrivalSeq(arrivalTime, verbose));
		if(verbose) System.out.println("adding new Arrival to Queue " + simulator.getSimulationEntity(getQueueID()).getName() + " (" + getQueueID() + ") at " + arrivalTime);
		if (simulator.getSimulationEntity(getQueueID()).getState() == 1) {  //should only occur when jobs was empty
			double departureTime = simulator.getCurrentSimTime() + jitter(1/simulator.getSimulationEntity(getQueueID()).getServiceRate());
			simulator.getEventList().putAway(new DepartureSeq(getQueueID(), departureTime, verbose));
			if(verbose) System.out.println("adding new Departure to Queue " + simulator.getSimulationEntity(getQueueID()).getName() + " (" + getQueueID() + ") at " + departureTime);
		}
	}
}