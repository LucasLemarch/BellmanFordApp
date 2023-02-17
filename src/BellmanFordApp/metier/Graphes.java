package BellmanFordApp.metier;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import org.graphstream.algorithm.Toolkit;

public class Graphes
{
	private SingleGraph graphe;

	public Graphes()
	{
		this.genererGrapheAlea(4, 5);
	}

	public SingleGraph getGraphe() { return this.graphe; }
	public int getNbNoeuds() {return this.graphe.getNodeCount(); }

	public void ajouterNoeud()
	{
		this.graphe.addNode("test");
	}

	public void genererGrapheAlea(int nbSommet, int nbArete)
	{
		this.graphe = new SingleGraph("Graphe Aléatoire");

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
			
			int val = (int)(Math.random() * 5) +1;
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
}