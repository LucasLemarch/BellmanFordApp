package BellmanFordApp.metier;

import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Edge;

import org.graphstream.algorithm.Toolkit;

public class Graphes
{
	public static SingleGraph genererGraphe( int nbSommet, int nbArete )
	{
		SingleGraph graph = new SingleGraph("Graphe Aléatoire");

		for(int i = 0 ; i < nbSommet ; i++)
			graph.addNode(""+i);

		for(int i = 0 ; i < nbArete ; i++)
		{
			Node noeud1, noeud2;

			do{
				noeud1 = Toolkit.randomNode(graph);
				noeud2 = Toolkit.randomNode(graph);
			} while(noeud1.hasEdgeBetween(noeud2) || noeud2.hasEdgeBetween(noeud1) || noeud1 == noeud2);

			Edge e = graph.addEdge("(" + noeud1.getId() + "," + noeud2.getId() + ")",
			              noeud1.getId(), noeud2.getId(), true);
			
			int val = (int)(Math.random() * 5) +1;
			e.setAttribute("label","" + val);
			e.setAttribute("valeur", val);
		}

		for(Node n:graph.getNodeSet()) 
		{
			n.setAttribute("ui.style","fill-color:red;");
			n.setAttribute("label",""+n.getId());
		}

		return graph;
	}

	public static void algorithmeBellmanFord(SingleGraph graph, int idNoeudDebut)
	{
		for(Node n:graph.getNodeSet()) 
		{
			n.removeAttribute("pere"); // enleve le pere si il existe
			n.setAttribute("poids", Integer.MAX_VALUE); // donne un poids "infini" aux noeuds
		}

		graph.getNode(idNoeudDebut).setAttribute("poids", 0); // poid de 0 au noeud de depart

		for (int i = 1; i <= graph.getNodeCount()-1; i++)
		{
			// debug
			System.out.println("    TOUR " + i);

			for(Node n:graph.getNodeSet()) 
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

			// debug
			for (Node n : graph.getNodeSet())
			{
				System.out.println("" + n.getId() + "    poids : " + n.getAttribute("poids") + "    pere : " + n.getAttribute("pere"));
			}
		}
	}

	public static void main(String args[])
	{
		SingleGraph graph = Graphes.genererGraphe(4, 5);
		graph.display();
		
		Graphes.algorithmeBellmanFord(graph, 0);
	}	
}