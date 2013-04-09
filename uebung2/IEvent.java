public interface IEvent {
	void eventExec(BasicSimulator simulator);
	int getExecTime();
}