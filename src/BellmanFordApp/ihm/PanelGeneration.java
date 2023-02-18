package BellmanFordApp.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BellmanFordApp.controleur.Controleur;

public class PanelGeneration extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private PanelSectionEntier panelSommet;
	private PanelSectionEntier panelArete;
	private PanelSectionEntier panelMin;
	private PanelSectionEntier panelMax;
	private JButton            btnGenerer;

	public PanelGeneration(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());

		// Création des composants
		JPanel panelCentre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelCentre.setPreferredSize(new Dimension(260, this.getHeight()));

		this.panelSommet = new PanelSectionEntier(ctrl, "Nombre de sommets");
		this.panelArete  = new PanelSectionEntier(ctrl, "Nombre d'aretes"  );
		this.panelMin    = new PanelSectionEntier(ctrl, "Poids minimum"    );
		this.panelMax    = new PanelSectionEntier(ctrl, "Poids maximum"    );
		this.btnGenerer  = new JButton("Generer");


		// Positionnement des composants
		this.add(new JLabel("Generer un graphe aléatoire"), BorderLayout.NORTH);

		this.add(panelCentre, BorderLayout.CENTER);
		panelCentre.add(this.panelSommet);
		panelCentre.add(this.panelArete );
		panelCentre.add(this.panelMin   );
		panelCentre.add(this.panelMax   );
		panelCentre.add(this.btnGenerer );
	

		// Activation des composants
		this.btnGenerer.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e) 
	{
	/*
	
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

		int nbAreteMax = nbSommet * (nbSommet - 1) / 2;
		if (nbArete > nbAreteMax)
			{
				// afficher erreur
				erreur = true;
			}

		if (!erreur)
			this.ctrl.genererGrapheAlea(nbSommet, nbArete);
	
		*/
	}
}
