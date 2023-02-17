package BellmanFordApp.ihm;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BellmanFordApp.controleur.Controleur;

public class FrameGraphe extends JFrame
{
	private Controleur ctrl;

	private PanelGraphe panelGraphe;
	private PanelBellmanFord panelBellmanFord;
	private PanelGeneration panelGeneration;

	public FrameGraphe(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());
		this.setSize(800, 800);

		// Création des composants
		JPanel panelOption = new JPanel(new GridLayout(3, 1));

		this.panelGraphe = new PanelGraphe(ctrl);
		this.panelBellmanFord = new PanelBellmanFord(ctrl);
		this.panelGeneration = new PanelGeneration(ctrl);

		// Positionnement des composants
		this.add(this.panelGraphe, BorderLayout.CENTER);
		this.add(panelOption, BorderLayout.EAST);

		panelOption.add(this.panelBellmanFord);
		panelOption.add(this.panelGeneration);
		panelOption.add(new JPanel());

		this.setVisible(true);
	}

	public void rechargerGraphe()
	{
		//maj panelGraphe
		this.remove(this.panelGraphe);

		this.panelGraphe = new PanelGraphe(ctrl);
		this.add(this.panelGraphe, BorderLayout.CENTER);

		this.revalidate();
		this.repaint();

		//maj panelBellmanFord
		this.panelBellmanFord.ajusterListes();
	}
}
