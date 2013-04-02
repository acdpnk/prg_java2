public abstract class BasicSimulator {
	double currentSimTime;
	double getCurrentQueueLength();
	double lastProbeTime;
	IEventList events();
	ISimulationEntity entity();
}