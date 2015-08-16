

public final class RandomNumberGenerator {
	public static int seed = 32;
	
	private static final int a = 16807;
	private static final int m = 2147483647;
	private static final int q = 127773;
	private static final int r = 2836;

	private RandomNumberGenerator() {
	}

	public static void setSeed(int s)
	{
		if(s<1 || s>=m)
		{
			throw new IllegalArgumentException("invalid seed");
		}
		seed=s;
		
	}
	public static double nextDouble() {
		// TODO Auto-generated method stub
		seed=a*(seed%q)-r*(seed/q);
		if(seed<0)
			seed+=m;
		return (double) seed/(double)m;
	
	}

}