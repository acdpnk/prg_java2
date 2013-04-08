public class Departure implements IEvent {
	private int execTime;

	protected Departure(int execTime){
		this.execTime=execTime;
	}

	public void eventExec(BasicSimulator simulator){
		System.out.println("Departure\n");
		if(simulator.entity.getState() > 0){
			simulator.events.putAway(new Departure(simulator.getCurrentSimTime() + (int)(Math.random()*6) + 1));
		}
		simulator.entity.setState(simulator.entity.getState()-1);
	}
	public int getExecTime(){
		return execTime;
	}
}