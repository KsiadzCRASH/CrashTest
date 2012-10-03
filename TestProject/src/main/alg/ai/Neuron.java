package main.alg.ai;

public abstract class Neuron implements INeuron 
{
	public final long nId;

	public INeuron[] parents;
	public INeuron[] children;
	
	public Neuron(INeuron parent)
	{
		nId = (long)Math.random() * this.hashCode() + this.hashCode();
	
		parents = new INeuron[10];
		children = new INeuron[10];
		
		children[0] = this;
		children[1] = parent;
	}
	
	protected INeuron findRoute(double [] data)
	{
		double distance = Double.MIN_VALUE;
		INeuron closest = null;
	
		for (int i = 0; i < children.length; i++) 
		{
			
		}
		
		return closest;
	}
	
	
}
