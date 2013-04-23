/**
 * contains main method, entry point for the program.
 */
public class Main{
	private static String file;
	private static simulatorcore.SimulatorSeq simulator;

	//default values for optional commandline arguments
	/**
	 * contains default value for number of queues
	 */
	public static final int NUMBER_OF_QUEUES = 2;
	/**
	 * contains default value for verboseness
	 */
	public static final boolean VERBOSE = false;
	/**
	 * contains default value for simulation end
	 */
	public static final double SIMULATION_END = 15000;
	/**
	 * contains default value for mode
	 */
	public static final int MODE = 2; // equal distribution

	/**
	 * Initializes and starts the simulation.
	 * @param  args accepts<br>
	 * -v (verbose mode, default is silent)<br>
	 * -n <b>int</b> (number of queues is <b>int</b>, default is 2)<br>
	 * -t <b>double</b> (simulation ends at time <b>double</b>, default is 300)<br>
	 * -e simulation runs in mode equal distribution (default): new arrivals are distributed randomly over all queues.<br>
	 * -s simulation runs in mode shortest: new arrivals are added to the shortest present queue at creation. if tied, one of the "shortest" queues is chosen at random.
	 */
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
			if (arg.equals("-t")) simulationEnd=Double.parseDouble(args[i+1]);
			i++;
		}

		int numberOfQueues = NUMBER_OF_QUEUES;
		i=0;
		for (String arg : args) {
			if (arg.equals("-n")) numberOfQueues=Integer.parseInt(args[i+1]);
			i++;
		}

		int mode = MODE;
		i=0;
		for (String arg : args) {
			if(arg.equals("-e") || arg.equals("-s") || arg.equals("-f")){
				if(arg.equals("-e")) mode = 0;
				if(arg.equals("-s")) mode = 1;
				if(arg.equals("-f")){
					mode = 2;
					file = args[i+1];
				}
				break;
			}
			i++;
		}
		// if(mode == 0 || mode == 1) {
		// 	simulator = new simulatorcore.Simulator(numberOfQueues, simulationEnd, verbose, mode);
		// }
		if(mode == 2) {
			simulator = new simulatorcore.SimulatorSeq(file, simulationEnd, verbose, mode);
		}
		simulator.init();
		simulator.run();
	}
}