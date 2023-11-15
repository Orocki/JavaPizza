package pizzaPackage;

public class Extras
{
	private String name;
	private double preis;
	
	public Extras(String name, double preis)
	{
		super();
		this.name = name;
		this.preis = preis;
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	public double getPreis()
	{
		return preis;
	}

	public void setPreis(double preis)
	{
		this.preis = preis;
	}

	
	@Override
	public String toString()
	{
		return name;
	}

	
}