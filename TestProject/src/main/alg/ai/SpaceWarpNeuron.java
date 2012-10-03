package main.alg.ai;

public class SpaceWarpNeuron extends Neuron 
{
	public SpaceWarpNeuron(INeuron parent) {
		super(parent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public <T> INeuron procesSignal(T data) {

		double [] nData = warpData((double []) data);
		
		
		return null;
	}
	
	// Data specific 
	private double [] warpData(double [] data)
	{
		return data;
	}

}
