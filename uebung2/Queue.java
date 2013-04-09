public class Queue implements ISimulationEntity{
	private int jobs;

	public int getState(){
		return jobs;
	}

	public void setState(int newState){
		jobs=newState;
	}
}