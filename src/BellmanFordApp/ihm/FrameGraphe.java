package BellmanFordApp.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import BellmanFordApp.controleur.Controleur;

public class FrameGraphe extends JFrame
{
	private Controleur ctrl;

	private PanelGraphe panelGraphe;
	private PanelOptions panelOptions;

	public FrameGraphe(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());
		this.setSize(800, 800);

		this.panelGraphe = new PanelGraphe();
		this.panelOptions = new PanelOptions(ctrl);

		this.add(this.panelGraphe, BorderLayout.CENTER);
		this.add(this.panelOptions, BorderLayout.EAST);

		this.setVisible(true);
	}
}
