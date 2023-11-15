package pizzaPackage;

public class Groesse
{
	private String name;
	private double faktor;
	
	public Groesse(String name, double faktor)
	{
		super();
		this.name = name;
		this.faktor = faktor;
	}
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public double getFaktor()
	{
		return faktor;
	}
	public void setFaktor(double faktor)
	{
		this.faktor = faktor;
	}

	
	@Override
	public String toString()
	{
		return name;
	}
	
}
