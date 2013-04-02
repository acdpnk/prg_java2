public interface IEvent {
	void eventExec(BasicSimulator simulator);
	double getExecTime();
}