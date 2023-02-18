package BellmanFordApp.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BellmanFordApp.controleur.Controleur;

public class PanelSectionEntier extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton    btnMoins;
	private JButton    btnPlus;
	private JTextField txtEntier;
	
	public PanelSectionEntier(Controleur ctrl, String nom)
	{
		this.ctrl = ctrl;

		this.setLayout(new FlowLayout());

		// Création des composants
		this.btnMoins = new JButton("-");
		this.btnPlus  = new JButton("+");
		this.txtEntier = new JTextField("0", 3);

		// Positionnement des composants
		this.add(new JLabel(String.format("%-20s", nom)));
		this.add(this.btnMoins);
		this.add(this.txtEntier);
		this.add(this.btnPlus);

		// Activation des composants
		this.btnMoins.addActionListener(this);
		this.btnPlus.addActionListener(this);
		this.txtEntier.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnMoins)
		{
			try 
			{
				int val = Integer.parseInt(this.txtEntier.getText());
				this.txtEntier.setText("" + (val-1));
			}
			catch( NumberFormatException ex )
			{
				// afficher erreur
			}
		}

		if (e.getSource() == this.txtEntier)
			this.txtEntier.setText(this.txtEntier.getText().replaceAll("\\D+",""));

		if (e.getSource() == this.btnPlus)
		{
			try 
			{
				int val = Integer.parseInt(this.txtEntier.getText());
				this.txtEntier.setText("" + (val+1));
			}
			catch( NumberFormatException ex )
			{
				// afficher erreur
			}
		}
	}

}
