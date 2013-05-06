package de.luh.sim.java13.ue3;

import java.io.File;

import de.luh.sim.java13.ue3.simulatorcore.BasicSimulator;
import de.luh.sim.java13.ue3.simulatorcore.Simulator;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		boolean debug = false;
		int simulationLength = 1500;
		File file = null;
		for (int i = 0; i < args.length; i++) {
			if(args[i].equals("-t")){
				simulationLength = Integer.parseInt(args[i+1]);
				i++;
			} else if(args[i].equals("-v")){
				debug = true;
			} else if(args[i].equals("-f")){
				file = new File(args[i+1]);
				i++;
			}
		}
		if(file == null){
			System.out.println("Keine Datei zum Simulieren gefunden");
			System.out.println("Beende Simulation");
			System.exit(0);
		}
		BasicSimulator meineSimulation = new Simulator(debug, simulationLength, file);
		meineSimulation.init();
		meineSimulation.run();
	}

}
