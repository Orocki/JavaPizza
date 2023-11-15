package pizzaPackage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SpeisekarteEinlesen
{
	public static void main(String[] args)
	{
	
		try
		{
			String zeile = null;
			BufferedReader einlesen = new BufferedReader(new FileReader("Speisekarte.txt"));
			try
			{
					while((zeile = einlesen.readLine()) != null)
				{
					System.out.println(zeile);
				}
			}
			catch(IOException ex)
			{
				System.out.println(ex.getMessage());
			}
			finally
			{
				einlesen.close();
			}
				
		}
		catch(IOException e)
		{
			System.out.println("Error");
		}
	
	}
}
