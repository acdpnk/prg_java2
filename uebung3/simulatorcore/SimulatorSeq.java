package simulatorcore;

import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import simulationmodel.*;
import simulatorcore.BasicSimulator;


public class SimulatorSeq extends BasicSimulator{
	/**
	 * number of queues to be simulated
	 */
	private int numberOfQueues;
	/**
	 * print debug information if true (is passed on to all other methods and classes employed)
	 */
	protected boolean verbose;
	/**
	 * time at which the simulation should end
	 */
	private double simulationEnd;
	/**
	 * number of cycles the simulation has run
	 */
	private int iterations;

	private int mode;


	/**
	 * getter for the simulation endpoint
	 * @return the simulations max runtime
	 */
	public double getSimulationEnd(){
		return simulationEnd;
	}

	/**
	 * getter for the simulation's mode
	 * @return the simulation's mode. 0: equal distribution, 1: shortest
	 */
	public int getMode(){
		return mode;
	}

	/**
	 * setter vor verboseness
	 * @param verboseness prints debug info if true
	 */
	protected void setVerboseness(boolean verboseness){
		verbose = verboseness;
	}

	private String modelFilePath;
	private String resultFilePath;
	private double arrivalRate;

	private double throughput;
	private double wip;
	private double processingTime;

	private ArrayList<QueueSeq> entity;

	public String getResultFilePath(){
		return resultFilePath;
	}

	public QueueSeq getSimulationEntity(int i){
		return entity.get(i);
	}

	// public int getNumberOfEntities(){
	// return entity.size();
	// }

	public SimulatorSeq(String file, double simulationEnd, boolean verbose, int mode){
		this.simulationEnd=simulationEnd;
		this.verbose=verbose;
		this.mode=mode;

		modelFilePath = file;
		resultFilePath = modelFilePath;
		currentQueueLength = new ArrayList<Double>();
		entity = new ArrayList<QueueSeq>();
	}

	public void init(){
		setCurrentSimTime(0);
		setLastProbeTime(0);
		setCompletedJobs(0);
		events = new EventListPQ();

		ArrayList<String> params = new ArrayList<String>();
		try {
			File modelFile = new File(modelFilePath);
			Scanner model = new Scanner(modelFile);


			while(model.hasNext()){
				if(model.hasNext("%.*")){
					model.nextLine();
				}
				else{
					params.add(model.next());
				}
			}
			model.close();
		}
		catch(Exception e){
			System.out.println(e + "\nCould not open file.\nStopping Program.\n");
			System.exit(1);
		}

		setArrivalRate(Double.parseDouble(params.get(0)));
		for (int i=1; i<params.size() ; i+=2) {
			currentQueueLength.add(0.0);
			entity.add(new QueueSeq(params.get(i), Double.parseDouble(params.get(i+1))));
			numberOfEntities++;
		}

		events.putAway(new ArrivalSeq(simulationmodel.Event.jitter(1/getArrivalRate()), verbose));
	}

	public void run(){
		IEvent event = events.nextEvent();
		while (event != null && getCurrentSimTime() < getSimulationEnd()) {
			setCurrentSimTime(event.getExecTime());
			refreshQueueLength();
			event.eventExec(this);
			event = events.nextEvent();
			if (verbose){
				System.out.println("\nSimulation Time: " + getCurrentSimTime() + "\n");
				for(int i=0; i<getNumberOfEntities(); i++) {
					System.out.println("\nQueue " + getSimulationEntity(i).getName() + "(" + i +"):" + "\nWaiting Jobs:    " + getSimulationEntity(i).getState() + "\nQueue Length:    " + getCurrentQueueLength(i) / getCurrentSimTime() + "\n");
				}
			}
		}
		throughput = getCompletedJobs()/getCurrentSimTime();
		wip = 0;
		for(int i=0; i<getNumberOfEntities(); i++){
			wip+=getCurrentQueueLength(i)/getCurrentSimTime();
		}
		processingTime = wip/throughput;

		try{
			PrintWriter resultFile = new PrintWriter(modelFilePath.split("\\.model")[0].concat("_result.txt"), "UTF-8");
			resultFile.println("Time simulated: " + getCurrentSimTime() + "\nArrival Rate: " + getArrivalRate());
			resultFile.println("\n%Queue Parameters:\n");
			for (int i=0; i<getNumberOfEntities(); i++){
				QueueSeq entity = getSimulationEntity(i);
				resultFile.println(entity.getName() + ": " + entity.getServiceRate() + ", " + getCurrentQueueLength(i)/getCurrentSimTime());
			}
			resultFile.println("\n%Simulation Parameters:" + "\nThroughput: " + throughput + "\nWIP: " + wip + "\nProcessing Time: " + processingTime);
			resultFile.close();
		}
		catch(Exception e){
			System.out.println(e + "\ncould not open file for writing... :(");
		}
		System.out.println("Time simulated: " + getCurrentSimTime() + "\nArrival Rate: " + getArrivalRate());
		System.out.println("\nQueue Parameters:\n");
		for (int i=0; i<getNumberOfEntities(); i++){
			QueueSeq entity = getSimulationEntity(i);
			System.out.println(entity.getName() + ": " + entity.getServiceRate() + ", " + getCurrentQueueLength(i)/getCurrentSimTime());
		}
		System.out.println("\nSimulation Parameters:" + "\nThroughput: " + throughput + "\nWIP: " + wip + "\nProcessing Time: " + processingTime);

		if(throughput > getArrivalRate()) System.out.println("\nThroughput exceeds Arrival Rate - System stable.\n");
		else System.out.println("\nThroughput below Arrival Rate - System unstable!\n");
	}
}