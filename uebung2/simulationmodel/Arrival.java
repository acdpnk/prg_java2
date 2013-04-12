package simulationmodel;
public class Arrival extends Event {
	private int execTime;

	public Arrival(int execTime){
		this.execTime=execTime;
	}

	public void eventExec(simulatorcore.BasicSimulator simulator){
		System.out.println("Arrival");
		simulator.getSimulationEntity().setState(simulator.getSimulationEntity().getState()+1);
		int arrivalTime = simulator.getCurrentSimTime() + (int)(Math.random()*10) + 1;
		simulator.getEventList().putAway(new Arrival(arrivalTime));
		if (simulator.getSimulationEntity().getState() == 1) {  //should only occur when jobs was empty
			simulator.getEventList().putAway(new Departure(arrivalTime + (int)(Math.random()*6 + 1)));
		}
	}
	public int getExecTime(){
		return execTime;
	}

}