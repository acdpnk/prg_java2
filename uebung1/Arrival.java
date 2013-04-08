public class Arrival implements IEvent {
	private int execTime;

	protected Arrival(int execTime){
		this.execTime=execTime;
	}

	public void eventExec(BasicSimulator simulator){
		simulator.entity.setState(simulator.entity.getState()+1);
		int arrivalTime = simulator.getCurrentSimTime() + (int)(Math.random()*10) + 1;
		simulator.events.putAway(new Arrival(arrivalTime));
		if (simulator.entity.getState() == 1) {  //should only occur when jobs was empty
			simulator.events.putAway(new Departure(arrivalTime + (int)(Math.random()*6 + 1)));
		}
	}
	public int getExecTime(){
		return execTime;
	}

}