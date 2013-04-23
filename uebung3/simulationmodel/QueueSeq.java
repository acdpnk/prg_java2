package simulationmodel;

public class QueueSeq extends Queue{
	private String name;
	private double serviceRate;

	public QueueSeq(String name, double rate){
		this.name = name;
		this.serviceRate = rate;
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}

	public void setServiceRate(double rate){
		this.serviceRate = rate;
	}
	public double getServiceRate(){
		return serviceRate;
	}
}