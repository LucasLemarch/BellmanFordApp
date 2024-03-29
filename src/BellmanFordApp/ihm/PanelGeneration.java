package BellmanFordApp.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		JPanel panelCentre = new JPanel(new GridLayout(5, 1));
		JPanel panelBouton = new JPanel();

		this.panelSommet = new PanelSectionEntier(ctrl, "Nombre de sommets");
		this.panelArete  = new PanelSectionEntier(ctrl, "Nombre d'aretes"  );
		this.panelMin    = new PanelSectionEntier(ctrl, "Poids minimum"    );
		this.panelMax    = new PanelSectionEntier(ctrl, "Poids maximum"    );
		this.btnGenerer  = new JButton("Generer");


		// Positionnement des composants
		this.add(new JLabel("Generer un graphe aléatoire", JLabel.CENTER), BorderLayout.NORTH);

		this.add(panelCentre, BorderLayout.CENTER);
		panelCentre.add(this.panelSommet);
		panelCentre.add(this.panelArete );
		panelCentre.add(this.panelMin   );
		panelCentre.add(this.panelMax   );
		panelCentre.add(     panelBouton);
		panelBouton.add(this.btnGenerer );

		// Activation des composants
		this.btnGenerer.addActionListener(this);
	}


	public void actionPerformed(ActionEvent e) 
	{
		int nbSommet = 0, nbArete = 0, pMin = 0, pMax = 0;
		boolean erreur = false;

		// Vérification de la valeur des sommets
		try {
			nbSommet = Integer.parseInt(this.panelSommet.getText());

			if (nbSommet < 2)
			{
				this.ctrl.afficherErreur("Le nombre de sommets doit être supérieur à 1");
				this.panelSommet.setColor(Color.RED);
				erreur = true;
			}
			else
				this.panelSommet.setColor(Color.WHITE);
		}
		catch( NumberFormatException ex )
		{
			this.ctrl.afficherErreur("Le nombre de sommets doit être un entier");
			this.panelSommet.setColor(Color.RED);
			erreur = true;
		}

		// Vérification de la valeur des sommets
		try {
			nbArete = Integer.parseInt(this.panelArete.getText());
			int nbAreteMax = nbSommet * (nbSommet - 1) / 2;

			if (nbArete < 1)
			{
				this.ctrl.afficherErreur("Le nombre d'aretes doit être supérieur à 0");
				this.panelArete.setColor(Color.RED);
				erreur = true;
			}
			else if (nbArete > nbAreteMax)
			{
				this.ctrl.afficherErreur("Le nombre d'aretes doit être inférieur à " + nbAreteMax);
				this.panelArete.setColor(Color.RED);
				erreur = true;
			}
			else
				this.panelArete.setColor(Color.WHITE);
		}
		catch( NumberFormatException ex )
		{
			this.ctrl.afficherErreur("Le nombre d'aretes doit être un entier");
			this.panelArete.setColor(Color.RED);
			erreur = true;
		}

		// Vérification de la valeur minimum du poids
		try {
			pMin = Integer.parseInt(this.panelMin.getText());
			this.panelMin.setColor(Color.WHITE);
		}
		catch( NumberFormatException ex )
		{
			this.ctrl.afficherErreur("Le poids minimum doit être un entier");
			this.panelMin.setColor(Color.RED);
			erreur = true;
		}

		// Vérification de la valeur maximum du poids
		try {
			pMax = Integer.parseInt(this.panelMax.getText());
			this.panelMax.setColor(Color.WHITE);
		}
		catch( NumberFormatException ex )
		{
			this.ctrl.afficherErreur("Le poids maximum doit être un entier");
			this.panelMax.setColor(Color.RED);
			erreur = true;
		}

		// Si aucune erreur on genere le graphe aleatoire
		if (!erreur)
			this.ctrl.genererGrapheAlea(nbSommet, nbArete, pMin, pMax);
	}
}
