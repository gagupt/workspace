import java.util.ArrayList;

public class Passenger {
	public static final double INITIAL_REPUTATION= 0.5;
	public static final double MIN_REPUTATION=0.2;
	public static final double MAX_REPUTATION=1.0;
	public static final double REPUTATION_DECAY_VALUE =0.05;
	public static final double REPUTATION_INCR_VALUE =0.02;

	
	public int id,TrainNo;
	public double ArrTime,nextJourneyTime;
	String Src,Dest,CurrStation,Status;
	static int Passenger_All=MainActivity.passenger_all;
	static int passengerId =0,TotalNumOfPassenger_Western=Passenger_All,
			TotalNumOfPassenger_Central=Passenger_All,TotalNumOfPassenger_Harbour=Passenger_All;
	public void setId(int id) {
		this.id = id;
	}

	public void setTrainNo(int trainNo) {
		TrainNo = trainNo;
	}

	public void setArrTime(double arrTime) {
		ArrTime = arrTime;
	}

	public void setSrc(String src) {
		Src = src;
	}

	public void setDest(String dest) {
		Dest = dest;
	}

	public void setStatus(String status) {
		Status = status;
	}
	public void setCurrStation(String CurrStation){
		this.CurrStation=CurrStation;
	}
	//array of reputations for all users
	
	
	public static double[] reputation = new double[3*Passenger_All];
	public double getNextJourneyTime() {
		return nextJourneyTime;
	}

	public void setNextJourneyTime(double nextJourneyTime) {
		this.nextJourneyTime = nextJourneyTime;
	}


	public static ArrayList<Passenger> ListOfPassenger_Western = 
			new ArrayList<Passenger>(TotalNumOfPassenger_Western);
	public static ArrayList<Passenger> ListOfPassenger_Central = 
			new ArrayList<Passenger>(TotalNumOfPassenger_Central);
	public static ArrayList<Passenger> ListOfPassenger_Harbour = 
			new ArrayList<Passenger>(TotalNumOfPassenger_Harbour);
	
	static int faultyPassenger[] = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
	
	public Passenger(int id , int TrainNo, double ArrTime
			,String Src,String Dest,String CurrStation,String Status,double nextJourneyTime) {
		this.id = id ;
		this.TrainNo = TrainNo;
		this.ArrTime = ArrTime;
		this.Src=Src;
		this.Dest=Dest;
		this.Status=Status;
		this.CurrStation=CurrStation;
	}

	public int getId() {
		return id;
	}
	public int getTrainNo() {
		return TrainNo;
	}
	public double getArrTime() {
		return ArrTime;
	}
	public String getSrc(){
		return Src;
	}
	public String getDest(){
		return Dest;
	}
	public String getStatus(){
		return Status;
	}
	public String getCurrStation(){
		return CurrStation;
	}

}
