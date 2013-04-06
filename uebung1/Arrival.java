public class Arrival implements IEvent {
	private double execTime;

	public Arrival(double execTime){
		this.execTime=execTime;
	}

	public void eventExec(BasicSimulator simulator){
		simulator.entity.setState(simulator.entity.getState()+1);
		simulator.events.putAway(new Arrival(simulator.getCurrentSimTime() + (int)(Math.random()*10) + 1));
	}
	public double getExecTime(){
		return execTime;
	}

}