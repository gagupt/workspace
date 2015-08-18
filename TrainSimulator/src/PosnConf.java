import java.util.ArrayList;


public class PosnConf {
	static int peakThres=5;
	double PosnConfidence,DistFromOriginMeter;
	int NumUserInputs;
	public boolean isPeak;
	
	static ArrayList<PosnConf> PosnConfidnce_List_Western = new ArrayList<PosnConf>(
			2000);
	static ArrayList<PosnConf> PosnConfidnce_List_Central = new ArrayList<PosnConf>(
			2000);
	static ArrayList<PosnConf> PosnConfidnce_List_Harbour = new ArrayList<PosnConf>(
			2000);
	public PosnConf( double distFromOriginMeter,double posnConfidence,
			int numUserInputs,boolean ispeak) {
		super();
		PosnConfidence = posnConfidence;
		DistFromOriginMeter = distFromOriginMeter;
		NumUserInputs = numUserInputs;
		isPeak=ispeak;
	}
	public double getPosnConfidence() {
		return PosnConfidence;
	}
	public void setPosnConfidence(double posnConfidence) {
		PosnConfidence = posnConfidence;
	}
	public double getDistFromOriginMeter() {
		return DistFromOriginMeter;
	}
	public void setDistFromOriginMeter(double distFromOriginMeter) {
		DistFromOriginMeter = distFromOriginMeter;
	}
	public int getNumUserInputs() {
		return NumUserInputs;
	}
	public void setNumUserInputs(int numUserInputs) {
		NumUserInputs = numUserInputs;
	}
	public boolean isPeak() {
		return isPeak;
	}

	public void setPeak(boolean isPeak) {
		this.isPeak = isPeak;
	}
}
