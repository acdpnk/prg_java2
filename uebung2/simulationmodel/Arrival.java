package simulationmodel;
public class Arrival extends Event {
	private double execTime;

	public Arrival(double execTime){
		this.execTime=execTime;
	}

	public void eventExec(simulatorcore.BasicSimulator simulator){
		System.out.println("Arrival");
		simulator.getSimulationEntity().setState(simulator.getSimulationEntity().getState()+1);
		double arrivalTime = simulator.getCurrentSimTime() + (Math.random()*10) + 1;
		simulator.getEventList().putAway(new Arrival(arrivalTime));
		if (simulator.getSimulationEntity().getState() == 1) {  //should only occur when jobs was empty
			simulator.getEventList().putAway(new Departure(arrivalTime + (Math.random()*6 + 1)));
		}
	}
	public double getExecTime(){
		return execTime;
	}

}