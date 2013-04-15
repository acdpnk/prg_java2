package simulatorcore;
import simulationmodel.*;
import java.util.ArrayList;

/**
 * a basic simulator simulating a variable number of entities
 */
public abstract class BasicSimulator {
	/**
	 * the current simulation time
	 */
	protected double currentSimTime;
	/**
	 * the last time the simulation time was checked, needed to calculate queue lengths
	 */
	protected double lastProbeTime;
	/**
	 * the list of events that affect the simulation, ordererd by time
	 */
	protected IEventList events;
	/**
	 * the number of completed jobs
	 */
	protected int completedJobs;
	/**
	 * the number of entities to be simulated
	 */
	protected int numberOfEntities;
	/**
	 * an array containing the lengths of the simulated queues
	 */
	protected double[] currentQueueLength;
	/**
	 * an array containing the simulated entities. an entity's id is their place in the array.
	 */
	protected ISimulationEntity[] entity;

	/**
	 * constructor for the basic simulator
	 * @param  numberOfEntities the number of entities to be simulated
	 */
	protected BasicSimulator(int numberOfEntities){
		this.numberOfEntities = numberOfEntities;
		currentQueueLength = new double[numberOfEntities];
		entity = new ISimulationEntity[numberOfEntities];
	}

	/**
	 * getter method for the number of entities
	 * @return number of entities
	 */
	public int getNumberOfEntities(){
		return numberOfEntities;
	}

	/**
	 * method to find the shortest/smallest entity according to their ordering
	 * @return the shortest queue's id. in case of multiple "shortest" queues, one of them is chosen randomly.
	 */
	public int getShortestEntityID(){
		int shortestEntityID=0;
		ArrayList<Integer> shortestEntities = new ArrayList<Integer>(getNumberOfEntities());

		for (int i=0; i<getNumberOfEntities(); i++) {
			if(getSimulationEntity(shortestEntityID).compareTo(getSimulationEntity(i))>=0) shortestEntityID=i;
		} // find the lowest present queuelength

		for (int i=0; i<getNumberOfEntities(); i++) {
			if(getSimulationEntity(shortestEntityID).compareTo(getSimulationEntity(i)) == 0){
				shortestEntities.add(i);
			}
		} // add the entity ids of all queues with that length to the list

		shortestEntities.trimToSize();
		return shortestEntities.get((int)(Math.random() * shortestEntities.size())); // return one of the entities' ids
	}

	/**
	 * setter method for the current simulation time
	 * @param time time the simulation should be set to
	 */
	public void setCurrentSimTime(double time){
		currentSimTime = time;
	}

	/**
	 * getter for the current simulation time
	 * @return current simulation time
	 */
	public double getCurrentSimTime(){
		return currentSimTime;
	}

	/**
	 * setter for queue lengths
	 * @param entityNumber the id of the entity to be changed
	 * @param length       the new length
	 */
	protected void setCurrentQueueLength(int entityNumber, double length){
		currentQueueLength[entityNumber] = length;
	}
	/**
	 * getter for queue lengths
	 * @param  entityNumber the id of the entity to be probed
	 * @return              the entity's length
	 */
	public double getCurrentQueueLength(int entityNumber){
		return currentQueueLength[entityNumber];
	}

	/**
	 * setter for last probe time
	 * @param time the timestamp lastProbeTime should be set to
	 */
	protected void setLastProbeTime(double time){
		lastProbeTime = time;
	}
	/**
	 * getter for last probe time
	 * @return last probe time
	 */
	public double getLastProbeTime(){
		return lastProbeTime;
	}

	/**
	 * setter for event list
	 * @param list the new eventlist
	 */
	protected void setEventList(IEventList list){
		events = list;
	}
	/**
	 * getter for eventlist
	 * @return the eventlist
	 */
	public IEventList getEventList(){
		return events;
	}

	/**
	 * setter for simulation entities
	 * @param entityID the id of the entity to be set
	 * @param entity       the entity object to be integrated into the simulation
	 */
	protected void setSimulationEntity(int entityID, ISimulationEntity entity){
		this.entity[entityID] = entity;
	}
	/**
	 * getter for simulation entities
	 * @param  entityID the id of the entity to get
	 * @return          the entity
	 */
	public ISimulationEntity getSimulationEntity(int entityID){
		return this.entity[entityID];
	}

	/**
	 * remove the first event from the simulator's eventlist
	 */
	public void removeFirstEvent(){
		events.removeFirstEvent();
	}

	/**
	 * put a new event on the simulator's eventlist
	 * @param event [description]
	 */
	protected void putInNewEvent(IEvent event){
		events.putAway(event);
	}

	/**
	 * refresh all queuelengths
	 */
	protected void refreshQueueLength(){
		double time = getCurrentSimTime() - getLastProbeTime();
		for (int i=0; i<getNumberOfEntities(); i++){
			currentQueueLength[i] += entity[i].getState()*time;
		}
		setLastProbeTime(getCurrentSimTime());
	}

	/**
	 * setter for the number of comleted jobs
	 * @param jobs new number of completed jobs
	 */
	public void setCompletedJobs(int jobs){
		this.completedJobs=jobs;
	}

	/**
	 * getter for number of completed jobs
	 * @return number of completed jobs
	 */
	public int getCompletedJobs(){
		return this.completedJobs;
	}

	/**
	 * initialise the simulator
	 */
	protected abstract void init();
	/**
	 * run the simulation
	 */
	protected abstract void run();

}