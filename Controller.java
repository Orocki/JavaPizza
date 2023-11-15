package pizzaPackage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class Controller
{
	private PizzaGUI gui;//gui anlegen
	DefaultListModel<Warenkorb> dlm2 = new DefaultListModel<Warenkorb>();
	ArrayList<Warenkorb> warenkorbL = new ArrayList<Warenkorb>();
	ArrayList<Pizza> pizzaL = new ArrayList<Pizza>();
	ArrayList<Extras> extrasL = new ArrayList<Extras>();
	ArrayList<Groesse> groesseL = new ArrayList<Groesse>();
	
	private double gesamt;
	private double einzelpreis;
//----------------Übernommen----------------------	
	public Controller()
	{
		this.gui = new PizzaGUI(this);
		registriereListener();
		fill();
	}
	
	private void registriereListener()
	{
		this.gui.setBtnRemoveALLListener(new BtnRemoveALLListener());
		this.gui.setComboBoxPizza(new ComboBoxPizzaListener());
		this.gui.setComboBoxExtras(new ComboBoxPizzaListener());
		this.gui.setComboBoxGroesse(new ComboBoxGroesseListener());
		this.gui.setBtnAdd(new BtnAddListener());
		this.gui.setBtnRemove(new BtnRemoveListener());
		this.gui.setBtnOrder(new BtnOrderListener());
	}
	
	class BtnRemoveALLListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			warenkorbL.clear();
			aktualisieren();
		}
		
	}
	class ComboBoxPizzaListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			aktualisierenEinzeln();// RUFT DIE AKTUALISIERENEINZELN FUNKTION FÜR DEN EINZELNEN GESAMTPREIS AUF, SOBALD ETWAS IN DER COMBOBOX VERÄNDERT WURDE
		}
		
	}
	class ComboBoxExtrasListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			aktualisierenEinzeln();
		}
		
	}
	class ComboBoxGroesseListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			aktualisierenEinzeln();	
		}
		
	}
	
	class BtnAddListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			warenkorbL.add(new Warenkorb(
					(Pizza) gui.comboBoxPizza.getSelectedItem(),
					(Extras) gui.comboBoxExtras.getSelectedItem(), 
					(Groesse) gui.comboBoxGroesse.getSelectedItem(),
					// FÜGT DURCH DEN BUTTON EIN WARENKORB OBJEKT IN DIE WARENKORBLISTE EIN, INKLUSIVE ERRECHNETER PREIS FÜR (PIZZA+ZUTATEN)*GROESSENFAKTOR
					aktualisierenEinzeln()
					));
			
			
			aktualisieren();// RUFT DIE AKTUALISIEREN FUNKTION AUF DAMIT WIEDER DER GESAMTPREIS ALLER PIZZEN, UND DIE LISTBOX AKTUALISIERT WERDEN
		}
		
	}
	class BtnRemoveListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			int selectedIndex = gui.list.getSelectedIndex();
			warenkorbL.remove(selectedIndex);
			// ENTFERNT DURCH DAS AUSWÄHLEN EINER PIZZA IN DER LISTBOX(list) UND DAS, AUSWÄHLEN DES BUTTONS DAS OBJEKT AUS DER WARENKORBLISTE
			aktualisieren();
		}
		
	}
	class BtnOrderListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent e)
		{
			alert();// DURCH DAS AUSWÄHLEN DES BESTELL BUTTONS WIRD DER KUNDE GEFRAGT OB ER WIRKLICH, DIE OBJEKTE AUS DER BESTELLLISTE BESTELLEN MÖCHTE
		}
	}
	
	public void alert()
	{
		JOptionPane.showMessageDialog(gui.btnOrder, warenkorbL, "Bestellvorgang abschließen?", JOptionPane.CANCEL_OPTION);
	}
		
	public double aktualisierenEinzeln()
	{
		einzelpreis = 0;// DER PREIS DER EINZELNEN PIZZA INKLUSIVE ALLER ZUSÄTZE WIRD AUF 0 GESETZT UND AKTUALISIERT
		einzelpreis = ((
				(Pizza) gui.comboBoxPizza.getSelectedItem()).getPreis()
				+ ((Extras) gui.comboBoxExtras.getSelectedItem()).getPreis())
				* ((Groesse) gui.comboBoxGroesse.getSelectedItem()).getFaktor();
		gui.lblPreisEinzel.setText(String.valueOf(einzelpreis));
		
		return einzelpreis;// EINZELPREIS WIRD ZURÜCKGEGEBEN DAMIT ANDERE METHODEN MIT DEM RÜCKGABEWERT, ARBEITEN KÖNNEN
	}
		
	public void aktualisieren()
	{
		
		dlm2.clear();// DEFAULTLISTMODEL WIRD GELEERT DAMIT ES DANACH DURCH DIE DURCHITERIERTE, WARENKORBLISTE GEFÜLLT WIRD
		gesamt = 0;// GESAMTPREIS ALLER PIZZEN WIRD GENULLT UND DANN ERNEUT AUF DEN AKTUALISIERTEN, PREIS GESETZT
		
		for (Warenkorb i : warenkorbL)
		{
			gesamt = gesamt + i.getPreis();
			dlm2.addElement(i);
		}
		gui.lblPreisGesamt.setText(String.valueOf(gesamt));
		gui.list.setModel(dlm2);
	}
	
	public void fill()
	{
		Pizza margaritap = new Pizza("Margarita", 6);
		Pizza salamip = new Pizza("Salami", 7);
		Pizza schinkenp = new Pizza("Schinken", 7);

		Extras leer = new Extras("----", 0);
		Extras salami = new Extras("Salami", 1);
		Extras schinken = new Extras("Schinken", 1);
		Extras ananas = new Extras("Ananas", 2);
			
		Groesse klein = new Groesse("Klein", 1);
		Groesse mittel = new Groesse("Mittel", 1.25);
		Groesse groß = new Groesse("Groß", 1.5);

		pizzaL.add(margaritap);
		pizzaL.add(salamip);
		pizzaL.add(schinkenp);

		extrasL.add(leer);
		extrasL.add(salami);
		extrasL.add(schinken);
		extrasL.add(ananas);

		groesseL.add(klein);
		groesseL.add(mittel);
		groesseL.add(groß);
			
		
		
		gui.getComboBoxPizza().setModel(new DefaultComboBoxModel<Pizza>(pizzaL.toArray(new Pizza[0])));
		gui.comboBoxExtras.setModel(new DefaultComboBoxModel<Extras>(extrasL.toArray(new Extras[0])));
		gui.comboBoxGroesse.setModel(new DefaultComboBoxModel<Groesse>(groesseL.toArray(new Groesse[0])));
	}
}
