package simulationmodel;
public interface IEvent {
	void eventExec(simulatorcore.BasicSimulator simulator);
	double getExecTime();
}