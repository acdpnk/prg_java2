/**
 * contains main method, entry point for the program.
 */
public class Main{

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
	public static final double SIMULATION_END = 100;
	/**
	 * contains default value for mode
	 */
	public static final int MODE = 0; // equal distribution

	/**
	 * Initializes and starts the simulation.
	 * @param  args accepts<br>
	 * -v (verbose mode, default is silent)<br>
	 * -n <b>int</b> (number of queues is <b>int</b>, default is 2)<br>
	 * -t <b>double</b> (simulation ends at time <b>double</b>, default is 100)<br>
	 * -m <b><0|1></b> (simulation runs in mode <b>0</b> (Equal Distribution: arrivals are randomly distributed over all queues) or <b>1</b> (Shortest: arrivals are added to the shortest queue), default is 0)
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
			if(arg.equals("-m")) mode = Integer.parseInt(args[i+1]);
			i++;
		}

		simulatorcore.Simulator simulator = new simulatorcore.Simulator(numberOfQueues, simulationEnd, verbose, mode);
		simulator.init();
		simulator.run();
	}
}