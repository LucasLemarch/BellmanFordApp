package BellmanFordApp.controleur;

import BellmanFordApp.ihm.FrameGraphe;

public class Controleur 
{
	private FrameGraphe ihm;

	public Controleur()
	{
		this.ihm = new FrameGraphe(this);
	}

	public static void main(String[] args)
	{
		new Controleur();
	}
}
