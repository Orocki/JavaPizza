package pizzaPackage;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JOptionPane;

public class PizzaGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblPreisGesamt;
	private JLabel lblPreisEinzel;

	private JButton btnAdd;
	private JButton btnRemove;
	private JButton btnOrder;


	private JComboBox comboBoxGroesse;
	private JComboBox comboBoxPizza;
	private JComboBox comboBoxExtras;

	private JList list;
	
	private double gesamt;
//---------------------------------------------
	// DefaultListModel dlm1 = new DefaultListModel();
	DefaultListModel dlm2 = new DefaultListModel<Warenkorb>();//Liste von Element Warenkorb in list anzeigen Also Liste erstellen
	ArrayList<Warenkorb> warenkorbL = new ArrayList<Warenkorb>();//Objekte speichern in warenkorbL von Warenkorb
	ArrayList<Pizza> pizzaL = new ArrayList<Pizza>();
	ArrayList<Extras> extrasL = new ArrayList<Extras>();
	ArrayList<Groesse> groesseL = new ArrayList<Groesse>();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					PizzaGUI frame = new PizzaGUI();
					frame.setVisible(true);
					frame.fill();//Bereich mit bestimmten Inhalt füllen
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PizzaGUI() {
		initialize();
	}

	private void initialize() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 745, 449);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblNewLabel());
		contentPane.add(getLblNewLabel_1());
		contentPane.add(getLblNewLabel_2());
		contentPane.add(getLblNewLabel_3());
		contentPane.add(getList());
		contentPane.add(getLblNewLabel_4());
		contentPane.add(getLblPreisGesamt());
		contentPane.add(getLblPreisEinzel());
		contentPane.add(getComboBoxGroesse());
		contentPane.add(getBtnAdd());
		contentPane.add(getBtnRemove());
		contentPane.add(getBtnOrder());
		contentPane.add(getComboBoxPizza());
		contentPane.add(getComboBoxExtras());

		JLabel lblNewLabel_5 = new JLabel("€");
		lblNewLabel_5.setBounds(266, 218, 14, 14);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_5_1 = new JLabel("€");
		lblNewLabel_5_1.setBounds(427, 36, 14, 14);
		contentPane.add(lblNewLabel_5_1);//Erstell Label für Beide mit Euro zeichen

		JButton btnRemoveALL = new JButton("Warenkorb leeren");//Erstellt Button RemoveAll
		btnRemoveALL.addActionListener(new ActionListener() //Listener um auf Eingabe zu warten AktionListener = Button press
		{
			public void actionPerformed(ActionEvent e) //AktionPerformed beim Clicken
			{
				warenkorbL.clear();
				aktualisieren();
			}
		});
		btnRemoveALL.setBounds(395, 215, 139, 23);
		contentPane.add(btnRemoveALL);
	}
//-------------------------------------------
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Pizza:");
			lblNewLabel.setBounds(10, 11, 46, 14);
		}
		return lblNewLabel;
	}//Unwichtig

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Extras:");
			lblNewLabel_1.setBounds(10, 36, 46, 14);
		}
		return lblNewLabel_1;
	}//Unwichtig

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Größe:");
			lblNewLabel_2.setBounds(293, 11, 46, 14);
		}
		return lblNewLabel_2;
	}//Unwichtig

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Preis:");
			lblNewLabel_3.setBounds(293, 36, 46, 14);
		}
		return lblNewLabel_3;
	}//Unwichtig

	private JList getList() {
		if (list == null) {
			list = new JList();
			list.setBounds(10, 59, 371, 149);
		}
		return list;
	}//Unwichtig

	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("Gesamtpreis:");
			lblNewLabel_4.setBounds(10, 219, 107, 14);
		}
		return lblNewLabel_4;
	}//Unwichtig

	private JLabel getLblPreisGesamt() {
		if (lblPreisGesamt == null) {
			lblPreisGesamt = new JLabel("0.00 ");//Wert in Label übergeben
			lblPreisGesamt.setBounds(234, 219, 46, 14);
		}
		return lblPreisGesamt;
	}//Unwichtig

	private JLabel getLblPreisEinzel() {
		if (lblPreisEinzel == null) {
			lblPreisEinzel = new JLabel("0.00 ");
			lblPreisEinzel.setBounds(395, 36, 46, 14);
		}
		return lblPreisEinzel;
	}//Unwichtig

	private JComboBox getComboBoxGroesse() {
		if (comboBoxGroesse == null) {
			comboBoxGroesse = new JComboBox();
			comboBoxGroesse.addItemListener(new ItemListener() //ItemListener für Check, Combo Boxen oder Radiobutton
			{
				public void itemStateChanged(ItemEvent e) //Für ItemListener wenn etwas in der Combobox ausgewählt wird
				{
					aktualisierenEinzeln();
				}
			});
			comboBoxGroesse.setBounds(360, 7, 123, 22);
		}
		return comboBoxGroesse;
	}

	private JButton getBtnAdd() {
		if (btnAdd == null) {
			btnAdd = new JButton("Hinzufügen");
			btnAdd.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{

					warenkorbL.add(new Warenkorb(//Added Werte zum Warenkorb(List)
							(Pizza) comboBoxPizza.getSelectedItem(),
							(Extras) comboBoxExtras.getSelectedItem(), 
							(Groesse) comboBoxGroesse.getSelectedItem(),
							aktualisierenEinzeln()//Aktualisert Preis in List
							));
					aktualisieren();//Aktualisiert beide Preise auserhalb von List
				}
			});
			btnAdd.setBounds(394, 69, 89, 23);
		}
		return btnAdd;
	}

	private JButton getBtnRemove() {
		if (btnRemove == null) {
			btnRemove = new JButton("Entfernen");
			btnRemove.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					int selectedIndex = list.getSelectedIndex();//SelectedIndex ist für Position in List zuständig , variable = positon von objekt in liste ,int weil Index als number gespeichert wirt
					warenkorbL.remove(selectedIndex);
					aktualisieren();
				}
			});
			btnRemove.setBounds(394, 103, 89, 23);
		}
		return btnRemove;
	}

	private JButton getBtnOrder() {
		if (btnOrder == null) {
			btnOrder = new JButton("Bestellen");
			btnOrder.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent e) 
				{
					alert();// Nur Alert ausgeführt
				}
			});
			btnOrder.setBounds(391, 137, 143, 71);
		}
		return btnOrder;
	}

	private JComboBox getComboBoxPizza() {
		if (comboBoxPizza == null) {
			comboBoxPizza = new JComboBox();
			comboBoxPizza.addItemListener(new ItemListener()//ItemListener weil ComboBox
			{
				public void itemStateChanged(ItemEvent e) 
				{
					aktualisierenEinzeln();//Nur aktualisieren
				}
			});
			comboBoxPizza.setBounds(59, 7, 165, 22);
		}
		return comboBoxPizza;
	}

	private JComboBox getComboBoxExtras() {
		if (comboBoxExtras == null) {
			comboBoxExtras = new JComboBox();
			comboBoxExtras.addItemListener(new ItemListener() 
			{
				public void itemStateChanged(ItemEvent e) 
				{
					aktualisierenEinzeln();//Same
				}
			});
			
			comboBoxExtras.addPropertyChangeListener(new PropertyChangeListener() 
			{
				public void propertyChange(PropertyChangeEvent evt)//Wenn ein Eigenschaft geändert wird kann darauf reagiert werden 
				{
				}
			});
			comboBoxExtras.setBounds(59, 32, 165, 22);

		}
		return comboBoxExtras;
	}
//---------------------------------------------AB Hier Methoden-----------------
	
	public void aktualisieren() {// Für Liste und preisgesamt

		dlm2.clear();
		gesamt = 0;//Clear alle daten in dlm2 und gesamt
		
		for (Warenkorb i : warenkorbL)//Erweiterte For-Schleife iteriert durch warenkorbL und auf jedes Warenkorb element zuzugreifen
		{
			gesamt = gesamt + i.getPreis();//Preis ausen i hat nichts mit gesamt zu tun nur unten
			
			dlm2.addElement(i);//Added den Preis der Bestellung in Liste

		}//For Schleife
		
		lblPreisGesamt.setText(String.valueOf(gesamt));// preisgesamt hat nun werte von var: gesamt von oben
		list.setModel(dlm2);//List zeigt nun Daten von dlm2 an

	}
	
//-----------------------------
	private double aktualisierenEinzeln() {//Für den Preis oben 
		
		double einzelpreis = 0;
		
		einzelpreis = (
				  ((Pizza) comboBoxPizza.getSelectedItem()).getPreis()//Holt preis von Klasse
				+ ((Extras) comboBoxExtras.getSelectedItem()).getPreis())
				* ((Groesse) comboBoxGroesse.getSelectedItem()).getFaktor();
		
		lblPreisEinzel.setText(String.valueOf(einzelpreis));//Setzt Wert für preis in label oben
		
		return einzelpreis;

	}
	
//-----------------------------
	public void alert() {//alert nur methode
		JOptionPane.showMessageDialog(btnOrder, warenkorbL, "Bestellvorgang abschließen?", JOptionPane.CANCEL_OPTION);
		//JOptionPane für Erstellung von Meldung in Dialogfenster  Cancel_option gibt SChaltfläche zum Abbrechen aus 
	}
//-----------------------------
	
	public void fill() {
		Pizza margaritap = new Pizza("Margarita", 6);//Objekt Pizza von Klasse Pizza in 
		Pizza salamip = new Pizza("Salami", 7);
		Pizza schinkenp = new Pizza("Schinken", 7);

		Extras leer = new Extras("----", 0);
		Extras salami = new Extras("Salami", 1);
		Extras schinken = new Extras("Schinken", 1);
		Extras ananas = new Extras("Ananas", 2);

		Groesse klein = new Groesse("Klein", 1);
		Groesse mittel = new Groesse("Mittel", 1.25);
		Groesse groß = new Groesse("Groß", 1.5);

		pizzaL.add(margaritap);//Added zur Combobox
		pizzaL.add(salamip);
		pizzaL.add(schinkenp);

		extrasL.add(leer);
		extrasL.add(salami);
		extrasL.add(schinken);
		extrasL.add(ananas);

		groesseL.add(klein);
		groesseL.add(mittel);
		groesseL.add(groß);

		comboBoxPizza.setModel(new DefaultComboBoxModel<Pizza>(pizzaL.toArray(new Pizza[0])));//Dafür da das in der ComboBox nun die Daten vom Array(oben) stehen
		comboBoxExtras.setModel(new DefaultComboBoxModel<Extras>(extrasL.toArray(new Extras[0])));
		comboBoxGroesse.setModel(new DefaultComboBoxModel<Groesse>(groesseL.toArray(new Groesse[0])));
		
		//toArray konvertiert Listenelemente in ein Array von Pizza
		//new DefaultComboBoxModel erstellt neues ComboBoxModell mit den Daten vom Array
		//comboBoxPizza.setModel setzt das Modell der ComboBox auf das neue DefaultComboBoxModel, sodass die ComboBox nun die Elemente aus pizzaL anzeigt und auswählt.

	}
}
