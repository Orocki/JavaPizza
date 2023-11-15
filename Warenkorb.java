package pizzaPackage;

public class Warenkorb
{
	private Pizza pizza;
	private Extras extras;
	private Groesse groesse;
	private double preis;
	
	public Warenkorb(Pizza pizza, Extras extras, Groesse groesse, double preis)
	{
		super();
		this.pizza = pizza;
		this.extras = extras;
		this.groesse = groesse;
		this.preis = preis;
	}

	

	public Pizza getPizza()
	{
		return pizza;
	}
	public void setPizza(Pizza pizza)
	{
		this.pizza = pizza;
	}
	public Extras getExtras()
	{
		return extras;
	}
	public void setExtras(Extras extras)
	{
		this.extras = extras;
	}
	public Groesse getGroesse()
	{
		return groesse;
	}
	public void setGroesse(Groesse groesse)
	{
		this.groesse = groesse;
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
		return pizza+"; "+groesse+"; "+extras+"; "+preis+" €";
	}
	
	
}
