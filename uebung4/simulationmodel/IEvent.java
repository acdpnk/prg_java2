/**
 * interface for event type objects
 */
package simulationmodel;
public interface IEvent {
	/**
	 * events should have a certain effect on the simulation. this function executes the event so that its effects become effective.
	 * @param simulator the simulator the event should affect.
	 */
	void eventExec(simulatorcore.SimulatorSeq simulator);
	/**
	 * events should have a point in time when they take place. this is the getter for it.
	 * @return the events timestamp.
	 */
	double getExecTime();
}