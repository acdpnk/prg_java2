package simulationmodel;
public class Arrival extends Event {
	private double execTime;

	public Arrival(double execTime, boolean verboseness){
		super(verboseness);
		this.execTime = execTime;
	}

	public Arrival(double execTime){
		this(execTime, false);
	}

	public void eventExec(simulatorcore.BasicSimulator simulator){
		if(verbose) System.out.println("Arrival at " + getExecTime());
		simulator.getSimulationEntity().setState(simulator.getSimulationEntity().getState()+1);
		double arrivalTime = simulator.getCurrentSimTime() + (Math.random()*10) + 1;
		simulator.getEventList().putAway(new Arrival(arrivalTime, verbose));
		if(verbose) System.out.println("added Arrival to Queue at " + arrivalTime);
		if (simulator.getSimulationEntity().getState() == 1) {  //should only occur when jobs was empty
			double departureTime = arrivalTime + (Math.random()*6 + 1);
			simulator.getEventList().putAway(new Departure(departureTime, verbose));
			if(verbose) System.out.println("added Departure to Queue at " + departureTime);
		}
	}
	public double getExecTime(){
		return execTime;
	}

}