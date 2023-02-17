package BellmanFordApp.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BellmanFordApp.controleur.Controleur;

public class PanelGeneration extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JTextField txtSommet;
	private JTextField txtArete;
	private JButton    btnGenerer;

	public PanelGeneration(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());

		// Création des composants
		JPanel panelTxt = new JPanel();
		this.txtSommet = new JTextField(5);
		this.txtArete  = new JTextField(5);
		this.btnGenerer = new JButton("Generer");


		// Positionnement des composants
		this.add(new JLabel("Generer un graphe aléatoire"), BorderLayout.NORTH);

		this.add(panelTxt, BorderLayout.CENTER);
		panelTxt.add(this.txtSommet);
		panelTxt.add(this.txtArete );

		this.add(this.btnGenerer, BorderLayout.SOUTH);
		

		// Activation des composants
		this.txtSommet.addActionListener(this);
		this.txtArete.addActionListener(this);
		this.btnGenerer.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.txtSommet)
		{
			try {
				int nbSommet = Integer.parseInt(this.txtSommet.getText());

				if (nbSommet < 2)
				{
					// afficher erreur
					this.txtSommet.setText("2");
				}
			}
			catch( NumberFormatException ex )
			{
				// afficher erreur
				this.txtSommet.setText("");
			}
		}

		if (e.getSource() == this.txtArete)
		{
			try {
				int nbSommet = Integer.parseInt(this.txtSommet.getText());
				int nbArete  = Integer.parseInt(this.txtArete .getText());
				int nbAreteMax = nbSommet * (nbSommet - 1) / 2;

				if (nbArete < 1)
				{
					// afficher erreur
					this.txtArete.setText("1");
				}

				if (nbArete > nbAreteMax)
				{
					// afficher erreur
					this.txtArete.setText("" + nbAreteMax);
				}
			}
			catch( NumberFormatException ex )
			{
				// afficher erreur
				this.txtArete.setText("");
			}
		}

		if (e.getSource() == this.btnGenerer)
		{
			int nbSommet = 0;
			int nbArete  = 0;
			boolean erreur = false;

			try {
				nbSommet = Integer.parseInt(this.txtSommet.getText());
				this.txtSommet.setBackground(Color.WHITE);
			}
			catch( NumberFormatException ex )
			{
				// afficher erreur
				this.txtSommet.setBackground(Color.RED);
				erreur = true;
			}

			try {
				nbArete  = Integer.parseInt(this.txtArete .getText());
				this.txtSommet.setBackground(Color.WHITE);
			}
			catch( NumberFormatException ex )
			{
				// afficher erreur
				this.txtArete.setBackground(Color.RED);
				erreur = true;
			}

			if (!erreur)
				this.ctrl.genererGrapheAlea(nbSommet, nbArete);
		}
		
	}
}
