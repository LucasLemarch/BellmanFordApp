package BellmanFordApp.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import BellmanFordApp.controleur.Controleur;

public class PanelBellmanFord extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JComboBox<String> cbDepart;
	private JComboBox<String> cbArrive;

	public PanelBellmanFord(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());

		// Création des composants
		JPanel panelChoix = new JPanel();
		this.cbDepart = new JComboBox<String>();
		this.cbArrive = new JComboBox<String>();

		for(int i = 0; i < this.ctrl.getNbNoeuds(); i++)
		{
			this.cbDepart.addItem("Noeud " + i);
			this.cbArrive.addItem("Noeud " + i);
		}


		// Positionnement des composants
		this.add(new JLabel("Algorithme de Bellman Ford"), BorderLayout.NORTH);

		this.add(panelChoix, BorderLayout.CENTER);
		panelChoix.add(this.cbDepart);
		panelChoix.add(this.cbArrive);


		// Activation des composants
		this.cbDepart.addActionListener(this);
		this.cbArrive.addActionListener(this);
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
	}

	public void actionPerformed(ActionEvent e) 
	{
		this.ctrl.algorithmeBellmanFord(this.cbDepart.getSelectedIndex(), 
		                                this.cbArrive.getSelectedIndex());
	}
}