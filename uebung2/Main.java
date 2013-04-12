public class Main{
	public static void main(String[] args) {
		boolean verbose=false;
		for (String arg : args) {
			if(arg.equals("-v")){
				verbose=true;
			}
		}

		double simulationEnd = 100;
		int i=0;
		for (String arg : args) {
			if (arg.equals("-t")) {
				simulationEnd=Double.parseDouble(args[i+1]);
			}
			i++;
		}

		simulatorcore.Simulator simulator = new simulatorcore.Simulator(simulationEnd, verbose);
		simulator.init();
		simulator.run();
	}
}