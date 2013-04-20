package simulationmodel;

import simulatorcore.BasicSimulator;

public class DepartureSeq extends Departure{
	protected DepartureSeq(int queueID, double execTime, boolean verboseness){
		super(queueID, execTime, verboseness);
	}

	public void eventExec(simulatorcore.BasicSimulator simulator){

		ISimulationEntity thisEntity = simulator.getSimulationEntity(getQueueID());

		simulator.removeFirstEvent();

		if(verbose) System.out.println("\n\nDeparture from Queue " + thisEntity.getName() + " (" + getQueueID() + ") at " + getExecTime());

		thisEntity.setState(thisEntity.getState()-1);

		if(thisEntity.getState() > 0) simulator.getEventList().putAway(new DepartureSeq(getQueueID(), getCurrentSimTime() + jitter(thisEntity.getServiceRate()), verbose));


		if (getQueueID()+1 < simulator.getNumberOfEntities()){
			ISimulationEntity nextEntity = simulator.getSimulationEntity(getQueueID() + 1);

			nextEntity.setState(nextEntity.getState() + 1);

			if (nextEntity.getState() == 1) {
				simulator.getEventList().putAway(new DepartureSeq(getQueueID()+1, getCurrentSimTime()+jitter(nextEntity.getServiceRate()), verbose));
			}
		}
		else {
			simulator.setCompletedJobs(simulator.getCompletedJobs()+1);
			if(verbose) System.out.println();
		}
	}
}