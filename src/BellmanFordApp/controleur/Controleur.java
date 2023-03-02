package BellmanFordApp.controleur;

import java.util.List;

import org.graphstream.graph.Graph;

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

	public void genererGrapheAlea(int nbSommet, int nbArete, int pMin, int pMax)
	{
		this.graphes.genererGrapheAlea(nbSommet, nbArete, pMin, pMax);
		this.ihm.rechargerGraphe();
	}

	public Graph getGraphe()
	{
		return this.graphes.getGraphe();
	}
	public int getNbNoeuds()
	{
		return this.graphes.getNbNoeuds();
	}

	public List<String> getLstArete()
	{
		return this.graphes.getLstArete();
	}

	public void algorithmeBellmanFord(int idNoeudDepart, int idNoeudArrive)
	{
		this.graphes.algorithmeBellmanFord(idNoeudDepart, idNoeudArrive);
	}

	public String getInfoGraphe()
	{
		return this.graphes.toString();
	}

	public void ajouterNoeud()
	{
		this.graphes.ajouterNoeud();
	}

	public void supprimerNoeud()
	{
		this.graphes.supprimerNoeud();
	}

	public boolean ajouterArete(String idNoeudDepart, String idNoeudArrive, int val)
	{
		return this.graphes.ajouterArete(idNoeudDepart, idNoeudArrive, val);
	}

	public void supprimerArete(String id)
	{
		this.graphes.supprimerArete(id);
	}

	public static void main(String[] args)
	{
		new Controleur();
	}
}
