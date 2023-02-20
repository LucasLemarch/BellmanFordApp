package BellmanFordApp.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import BellmanFordApp.controleur.Controleur;

public class PanelManipulation extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JButton btnAddNoeud;

	private JComboBox<String> cbDepart;
	private JComboBox<String> cbArrive;
	private JTextField        txtValeur;
	private JButton           btnAddArete;

	private JButton btnDelNoeud;

	private JComboBox<String> cbArete;
	private JButton           btnDelArete;

	public PanelManipulation(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());
		this.setBackground(Color.LIGHT_GRAY);

		// Creation des composants
		JPanel panelCentre = new JPanel(new GridLayout(4, 1));
		panelCentre.setOpaque(false);

		JPanel panelAddNoeud = new JPanel();
		panelAddNoeud.setOpaque(false);
		this.btnAddNoeud = new JButton("Ajouter Noeud");

		JPanel panelAddArete = new JPanel();
		panelAddArete.setOpaque(false);
		this.cbDepart = new JComboBox<String>();
		this.cbArrive = new JComboBox<String>();
		this.txtValeur = new JTextField(5);
		this.btnAddArete = new JButton("Ajouter Arete");

		for(int i = 0; i < this.ctrl.getNbNoeuds(); i++)
		{
			this.cbDepart.addItem("Noeud " + i);
			this.cbArrive.addItem("Noeud " + i);
		}

		JPanel panelDelNoeud = new JPanel();
		panelDelNoeud.setOpaque(false);
		this.btnDelNoeud = new JButton("Supprimer Noeud");

		JPanel panelDelArete = new JPanel();
		panelDelArete.setOpaque(false);
		this.cbArete = new JComboBox<String>();
		this.btnDelArete = new JButton("Supprimer Arete");
		
		for (String arete : this.ctrl.getLstArete())
			this.cbArete.addItem(arete);

		// Positionnement des composants
		this.add(new JLabel("Manipulation du graphe", JLabel.CENTER), BorderLayout.NORTH);
		this.add(panelCentre, BorderLayout.CENTER);

		panelCentre.add(panelAddNoeud);
		panelAddNoeud.add(this.btnAddNoeud);

		panelCentre.add(panelAddArete);
		panelAddArete.add(this.cbDepart);
		panelAddArete.add(this.cbArrive);
		panelAddArete.add(this.txtValeur);
		panelAddArete.add(this.btnAddArete);

		panelCentre.add(panelDelNoeud);
		panelDelNoeud.add(this.btnDelNoeud);

		panelCentre.add(panelDelArete);
		panelDelArete.add(this.cbArete);
		panelDelArete.add(this.btnDelArete);

		// Activation des composants
		this.btnAddNoeud.addActionListener(this);
		this.btnAddArete.addActionListener(this);
		this.btnDelNoeud.addActionListener(this);
		this.btnDelArete.addActionListener(this);
	}

	public void ajusterListes()
	{
		int nbNoeuds = this.ctrl.getNbNoeuds();

		while (this.cbDepart.getItemCount() != nbNoeuds)
		{
			if (this.cbDepart.getItemCount() < nbNoeuds)
			{
				this.cbDepart.addItem("Noeud " + this.cbDepart.getItemCount());
				this.cbArrive.addItem("Noeud " + this.cbArrive.getItemCount());
			}
			else
			{
				this.cbDepart.removeItemAt(this.cbDepart.getItemCount() - 1);
				this.cbArrive.removeItemAt(this.cbArrive.getItemCount() - 1);
			}
		}


		while (this.cbArete.getItemCount() == 0)
			this.cbArete.removeItemAt(0);

		for (String arete : this.ctrl.getLstArete())
			this.cbArete.addItem(arete);
	}

	public void actionPerformed(ActionEvent e) 
	{
		if (e.getSource() == this.btnAddNoeud)
		{
			this.ctrl.ajouterNoeud();

			this.cbDepart.addItem("Noeud " + this.cbDepart.getItemCount());
			this.cbArrive.addItem("Noeud " + this.cbArrive.getItemCount());
		}

		if (e.getSource() == this.btnAddArete)
		{
			try
			{
				String idDepart = ("" + this.cbDepart.getSelectedItem()).substring(6);
				String idArrive = ("" + this.cbArrive.getSelectedItem()).substring(6);
				System.out.println(idDepart);
				int valeur = Integer.parseInt(this.txtValeur.getText());

				if (idDepart.equals(idArrive))
				{
					// afficher erreur
				}
				else if (!this.ctrl.ajouterArete(idDepart, idArrive, valeur))
				{
					// afficher erreur
				}
				else
				{
					this.cbArete.addItem("(" + idDepart + "," + idArrive + ")");
				}
			}
			catch( NumberFormatException ex )
			{
				// afficher erreur
			}
		}

		if (e.getSource() == this.btnDelNoeud)
		{
			this.ctrl.supprimerNoeud();

			this.cbDepart.removeItemAt(this.cbDepart.getItemCount() - 1);
			this.cbArrive.removeItemAt(this.cbArrive.getItemCount() - 1);
		}

		if (e.getSource() == this.btnDelArete)
		{
			this.ctrl.supprimerArete("" + this.cbArete.getSelectedItem());
			this.cbArete.removeItem(this.cbArete.getSelectedItem());
		}
	}
}
