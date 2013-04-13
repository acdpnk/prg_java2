package simulationmodel;
public class Departure extends Event {
	private double execTime;

	protected Departure(int queueID, double execTime, boolean verboseness){
		super(queueID, verboseness);
		this.execTime = execTime;
	}

	protected Departure(double execTime, boolean verboseness){
		this(0, execTime, verboseness);
	}

	protected Departure(double execTime){
		this(execTime, false);
	}

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
	public double getExecTime(){
		return execTime;
	}
}