package simulationmodel;
public class Departure extends Event {
	private double execTime;

	protected Departure(double execTime, boolean verboseness){
		super(verboseness);
		this.execTime = execTime;
	}

	protected Departure(double execTime){
		this(execTime, false);
	}

	public void eventExec(simulatorcore.BasicSimulator simulator){
		if(verbose) System.out.println("\n\nDeparture at " + getExecTime());
		if(simulator.getSimulationEntity(0).getState() > 1){
			double departureTime = simulator.getCurrentSimTime() + (Math.random()*6) + 1;
			simulator.getEventList().putAway(new Departure(departureTime, verbose));
			if(verbose) System.out.println("adding new Departure to Queue at " + departureTime);
		}
		simulator.getSimulationEntity(0).setState(simulator.getSimulationEntity(0).getState()-1);
		simulator.setCompletedJobs(simulator.getCompletedJobs()+1);
	}
	public double getExecTime(){
		return execTime;
	}
}