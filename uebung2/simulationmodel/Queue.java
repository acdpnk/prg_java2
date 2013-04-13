package simulationmodel;
public class Queue implements ISimulationEntity, Comparable<ISimulationEntity>{
	private int jobs;

	public int getState(){
		return jobs;
	}

	public void setState(int newState){
		jobs=newState;
	}

	public int compareTo(ISimulationEntity entity){
		if(this.getState()>entity.getState()) return 1;
		else if(this.getState()<entity.getState()) return -1;
		else return 0;
	}
}