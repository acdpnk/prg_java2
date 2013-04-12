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
		if(verbose) System.out.println("Departure at " + getExecTime());
		if(simulator.getSimulationEntity().getState() > 1){
			double departureTime = simulator.getCurrentSimTime() + (Math.random()*6) + 1;
			simulator.getEventList().putAway(new Departure(departureTime, verbose));
			if(verbose) System.out.println("added Departure to Queue at " + departureTime);
		}
		simulator.getSimulationEntity().setState(simulator.getSimulationEntity().getState()-1);
	}
	public double getExecTime(){
		return execTime;
	}
}