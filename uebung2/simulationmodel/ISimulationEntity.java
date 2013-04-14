package simulationmodel;
/**
 * interface for the entity to be simulated. comparable by their state. (ISimulationEntity)
 */
public interface ISimulationEntity extends Comparable<ISimulationEntity>{
	/**
	 * getter for the entity's state (getZustand())
	 * @return the entity's state as an integer
	 */
	int getState();

	/**
	 * setter for the entity's state (setZustand())
	 * @param newState the entitys new state as an integer
	 */
	void setState(int newState);
	/**
	 * compareTo method
	 * @param  entity the entity this entity is to be compared to
	 * @return        1, 0 or -1, depending on ordering
	 */
	int compareTo(ISimulationEntity entity);
}