import java.util.Scanner;
import java.io.File;

public class SimulatorSeq extends Simulator{
	String modelFilePath;
	public SimulatorSeq(String file, double simulationEnd, boolean verbose, int mode){
		setSimulationEnd(simulationEnd);
		setVerboseness(verbose);
		setMode(mode);
		modelFilePath = file;
		currentQueueLength = new ArrayList<Double>();
		entity = new ArrayList<ISimulationEntity>();
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

		setArrivalRate(params.get(0).parseDouble());
		for (int i=1; i<params.size() ; i+=2) {
			currentQueueLength.add(0);
			entity.add(new QueueSeq(params.get(i), params.get(i+1).parseDouble()));
		}

		events.putAway(new ArrivalSeq(this, simulationmodel.Event.jitter(1/getArrivalRate()), verbose));
	}
}