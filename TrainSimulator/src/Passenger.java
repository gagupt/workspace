import java.util.ArrayList;

public class Passenger {
	public int id,TrainNo;
	public double ArrTime;
	String Src,Dest,CurrStation,Status;
	static int Passenger_All=50000;
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
	public static ArrayList<Passenger> ListOfPassenger_Western = 
			new ArrayList<Passenger>(TotalNumOfPassenger_Western);
	public static ArrayList<Passenger> ListOfPassenger_Central = 
			new ArrayList<Passenger>(TotalNumOfPassenger_Central);
	public static ArrayList<Passenger> ListOfPassenger_Harbour = 
			new ArrayList<Passenger>(TotalNumOfPassenger_Harbour);
	
	public Passenger(int id , int TrainNo, double ArrTime
			,String Src,String Dest,String CurrStation,String Status) {
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
