public class Departure extends Event {
	private int execTime;

	protected Departure(int execTime){
		this.execTime=execTime;
	}

	public void eventExec(BasicSimulator simulator){
		System.out.println("Departure");
		if(simulator.entity.getState() > 1){
			simulator.events.putAway(new Departure(simulator.getCurrentSimTime() + (int)(Math.random()*6) + 1));
		}
		simulator.entity.setState(simulator.entity.getState()-1);
	}
	public int getExecTime(){
		return execTime;
	}
}