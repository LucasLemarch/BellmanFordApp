package BellmanFordApp.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.FlowLayout;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import BellmanFordApp.controleur.Controleur;

public class PanelBellmanFord extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private JComboBox<String> cbDepart;
	private JComboBox<String> cbArrive;
	private JTextArea         taInfo;

	public PanelBellmanFord(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());

		// Création des composants
		JPanel panelChoix = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelChoix.setPreferredSize(new Dimension(260, this.getHeight()));
		this.cbDepart = new JComboBox<String>();
		this.cbArrive = new JComboBox<String>();

		for(int i = 0; i < this.ctrl.getNbNoeuds(); i++)
		{
			this.cbDepart.addItem("Noeud " + i);
			this.cbArrive.addItem("Noeud " + i);
		}

		
		this.taInfo = new JTextArea();
		this.taInfo.setEditable(false);

		JScrollPane panelScroll = new JScrollPane(this.taInfo);
		panelScroll.setPreferredSize(new Dimension(200, 200));
		


		// Positionnement des composants
		this.add(new JLabel("Algorithme de Bellman Ford", JLabel.CENTER), BorderLayout.NORTH);

		this.add(panelChoix, BorderLayout.CENTER);
		panelChoix.add(this.cbDepart);
		panelChoix.add(this.cbArrive);
		panelChoix.add(panelScroll);


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

		this.taInfo.setText(this.ctrl.getInfoGraphe());
	}
}