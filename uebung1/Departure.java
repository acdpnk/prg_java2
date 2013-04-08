public class Departure implements IEvent {
	private int execTime;

	protected Departure(int execTime){
		this.execTime=execTime;
	}

	public void eventExec(BasicSimulator simulator){
		simulator.entity.setState(simulator.entity.getState()-1);
		if(simulator.entity.getState() > 0){
			simulator.events.putAway(new Departure(simulator.getCurrentSimTime() + (int)(Math.random()*6) + 1));
		}
	}
	public int getExecTime(){
		return execTime;
	}
}