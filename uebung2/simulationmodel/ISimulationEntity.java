package simulationmodel;
public interface ISimulationEntity extends Comparable<ISimulationEntity>{
	int getState();
	void setState(int newState);
	int compareTo(ISimulationEntity entity);
}