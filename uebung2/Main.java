public class Main{

	/*
	 * Standard values for optional commandline arguments
	 */
	public static final int NUMBER_OF_QUEUES = 2;
	public static final boolean VERBOSE = false;
	public static final double SIMULATION_END = 100;

	public static void main(String[] args) {
		boolean verbose=VERBOSE;
		for (String arg : args) {
			if(arg.equals("-v")){
				verbose=true;
			}
		}

		double simulationEnd = SIMULATION_END;
		int i=0;
		for (String arg : args) {
			if (arg.equals("-t")) {
				simulationEnd=Double.parseDouble(args[i+1]);
			}
			i++;
		}

		int numberOfQueues = NUMBER_OF_QUEUES;
		i=0;
		for (String arg : args) {
			if (arg.equals("-n")) {
				numberOfQueues=Integer.parseInt(args[i+1]);
			}
			i++;
		}

		simulatorcore.Simulator simulator = new simulatorcore.Simulator(numberOfQueues, simulationEnd, verbose);
		simulator.init();
		simulator.run();
	}
}