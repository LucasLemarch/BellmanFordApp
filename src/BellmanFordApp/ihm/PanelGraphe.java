package BellmanFordApp.ihm;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelGraphe extends JPanel
{
	public PanelGraphe()
	{
		this.add(new JLabel("graphe"));
		this.setBackground(Color.YELLOW);

		this.setVisible(true);
	}
}
