package BellmanFordApp.ihm;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import BellmanFordApp.controleur.Controleur;

public class PanelOptions extends JPanel 
{
	private Controleur ctrl;

	public PanelOptions(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.add(new JLabel("options"));
		this.setBackground(Color.BLUE);

		this.setVisible(true);
	}
}
