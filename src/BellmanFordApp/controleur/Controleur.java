package BellmanFordApp.controleur;

import org.graphstream.graph.implementations.SingleGraph;

import BellmanFordApp.ihm.FrameGraphe;
import BellmanFordApp.metier.Graphes;

public class Controleur 
{
	private FrameGraphe ihm;
	private Graphes     graphes;

	public Controleur()
	{
		this.graphes = new Graphes();
		this.ihm = new FrameGraphe(this);
	}

	public void genererGrapheAlea(int nbSommet, int nbArete)
	{
		this.graphes.genererGrapheAlea(nbSommet, nbArete);
		this.ihm.rechargerGraphe();
	}

	public SingleGraph getGraphe()
	{
		return this.graphes.getGraphe();
	}

	public static void main(String[] args)
	{
		new Controleur();
	}
}
