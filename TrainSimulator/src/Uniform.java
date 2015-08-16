
import RandomVariable.RandomVariable;


public class Uniform implements RandomVariable{

	protected double u=0.0;
	protected double v=1.0;
	public Uniform(double u,double v)
	{
		this.u=u;
		this.v=v;
	}
	public double nextDouble()
	{
		return u+(v-u)*RandomNumberGenerator.nextDouble();
	}
	
}