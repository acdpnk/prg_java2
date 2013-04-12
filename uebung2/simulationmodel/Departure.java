package simulationmodel;
public class Departure extends Event {
	private int execTime;

	protected Departure(int execTime){
		this.execTime=execTime;
	}

	public void eventExec(simulatorcore.BasicSimulator simulator){
		System.out.println("Departure");
		if(simulator.getSimulationEntity().getState() > 1){
			simulator.getEventList().putAway(new Departure(simulator.getCurrentSimTime() + (int)(Math.random()*6) + 1));
		}
		simulator.getSimulationEntity().setState(simulator.getSimulationEntity().getState()-1);
	}
	public int getExecTime(){
		return execTime;
	}
}