package BellmanFordApp.metier;

import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;

import java.util.ArrayList;
import java.util.List;

import org.graphstream.algorithm.Toolkit;

public class Graphes
{
	private Graph graphe;

	public Graphes()
	{
		this.genererGrapheAlea(6, 14, -2, 10);
	}

	public Graph getGraphe() { return this.graphe; }
	public int getNbNoeuds() {return this.graphe.getNodeCount(); }

	public List<String> getLstArete()
	{
		ArrayList<String> lstArete = new ArrayList<String>();

		for (Edge e : this.graphe.getEdgeSet())
			lstArete.add(e.getId());
		
		return lstArete;
	}

	public void ajouterNoeud()
	{
		Node n = this.graphe.addNode("" + this.graphe.getNodeCount());

		n.setAttribute("ui.style","fill-color:red;");
		n.setAttribute("label",""+n.getId());
	}

	public boolean supprimerNoeud()
	{
		try {
			this.graphe.removeNode(this.graphe.getNodeCount() - 1);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

	public boolean ajouterArete(String idNoeudDepart, String idNoeudArrive, int val)
	{
		Node noeud1 = this.graphe.getNode(idNoeudDepart);
		Node noeud2 = this.graphe.getNode(idNoeudArrive);

		if (noeud1.hasEdgeBetween(noeud2) || noeud2.hasEdgeBetween(noeud1) || noeud1 == noeud2)
			return false;
		else
		{
			Edge e = this.graphe.addEdge("(" + noeud1.getId() + "," + noeud2.getId() + ")",
		                                 noeud1.getId(), noeud2.getId(), true);

			e.setAttribute("label","" + val);
			e.setAttribute("valeur", val);
		}

		return true;
	}

	public boolean supprimerArete(String id)
	{
		try {
			this.graphe.removeEdge(id);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	public void genererGrapheAlea(int nbSommet, int nbArete, int pMin, int pMax)
	{
		this.graphe = new MultiGraph("Graphe Aléatoire");

		for(int i = 0 ; i < nbSommet ; i++)
			this.graphe.addNode(""+i);

		for(int i = 0 ; i < nbArete ; i++)
		{
			Node noeud1, noeud2;

			do{
				noeud1 = Toolkit.randomNode(this.graphe);
				noeud2 = Toolkit.randomNode(this.graphe);
			} while(noeud1.hasEdgeBetween(noeud2) || noeud2.hasEdgeBetween(noeud1) || noeud1 == noeud2);

			Edge e = this.graphe.addEdge("(" + noeud1.getId() + "," + noeud2.getId() + ")",
			              noeud1.getId(), noeud2.getId(), true);
			
			int val = (int) (Math.random() * (pMax - pMin + 1) + pMin);
			e.setAttribute("label","" + val);
			e.setAttribute("valeur", val);
		}

		for(Node n:this.graphe.getNodeSet()) 
		{
			n.setAttribute("ui.style","fill-color:red;");
			n.setAttribute("label",""+n.getId());
		}
	}

	public void algorithmeBellmanFord(int idNoeudDepart, int idNoeudArrive)
	{
		for(Node n:this.graphe.getNodeSet()) 
		{
			n.removeAttribute("pere"); // enleve le pere si il existe
			n.setAttribute("poids", Integer.MAX_VALUE); // donne un poids "infini" aux noeuds
		}

		this.graphe.getNode(idNoeudDepart).setAttribute("poids", 0); // poid de 0 au noeud de depart

		for (int i = 1; i <= this.graphe.getNodeCount()-1; i++)
		{
			for(Node n:this.graphe.getNodeSet()) 
			{
				if (n.hasAttribute("poids") && (int) (n.getAttribute("poids")) != Integer.MAX_VALUE)
				{
					for (Edge e : n.getEachLeavingEdge())
					{
						int pArrive = e.getTargetNode().getAttribute("poids");
						int pDepart = n.getAttribute("poids");
						int valeur  = e.getAttribute("valeur");

						if (pArrive == Integer.MAX_VALUE || pArrive > pDepart + valeur)
						{
							e.getTargetNode().setAttribute("poids", pDepart + valeur);
							e.getTargetNode().setAttribute("pere", n.getId());
						}
					}
				}
			}
		}

		this.afficherChemin(idNoeudDepart, idNoeudArrive);
	}

	private boolean verifierCycleNegatif()
	{
		for(Node n:this.graphe.getNodeSet()) 
		{
			if (n.hasAttribute("poids") && (int) (n.getAttribute("poids")) != Integer.MAX_VALUE)
			{
				for (Edge e : n.getEachLeavingEdge())
				{
					int pArrive = e.getTargetNode().getAttribute("poids");
					int pDepart = n.getAttribute("poids");
					int valeur  = e.getAttribute("valeur");

					if (pArrive == Integer.MAX_VALUE || pArrive > pDepart + valeur)
					{
						return true;
					}
				}
			}
		}

		return false;
	}

	private void afficherChemin(int idNoeudDepart, int idNoeudArrive)
	{
		// Remettre toutes les aretes de la meme couleur
		for (Edge e : this.graphe.getEachEdge())
			e.setAttribute("ui.style","fill-color:black;");

		// Afficher le chemin le plus rapide
		Node n = this.graphe.getNode(idNoeudArrive);

		while (Integer.parseInt(n.getId()) != idNoeudDepart && (int) n.getAttribute("poids") != Integer.MAX_VALUE)
		{
			for (Edge e : n.getEachEnteringEdge())
			{
				if (e.getSourceNode().getId() == n.getAttribute("pere"))
				{
					e.setAttribute("ui.style","fill-color:green;");
					n = e.getSourceNode();
					break;                                                                    
				}
			}
		}
	}

	public String toString()
	{
		String sRet = "id   poids    pere\n";

		for (Node n : this.graphe.getNodeSet())
		{
			sRet += String.format("%-5s", n.getId());

			if ((int) n.getAttribute("poids") == Integer.MAX_VALUE)
				sRet += "infini      ";
			else
				sRet += String.format("%-12d", (int) n.getAttribute("poids"));

			sRet += n.getAttribute("pere") + "\n";
		}

		if(this.verifierCycleNegatif())
			sRet += "Il y a un cycle negatif";
		else
			sRet += "Il n'y a pas de cycle negatif";


		return sRet;
	}
}