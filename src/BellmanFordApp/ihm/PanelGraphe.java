package BellmanFordApp.ihm;

import java.awt.BorderLayout;

import javax.swing.JPanel;

import org.graphstream.graph.Graph;
import org.graphstream.ui.swingViewer.View;
import org.graphstream.ui.swingViewer.Viewer;
import org.graphstream.ui.swingViewer.Viewer.ThreadingModel;

import BellmanFordApp.controleur.Controleur;

public class PanelGraphe extends JPanel
{
	private Controleur ctrl;

	public PanelGraphe(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		Graph graphe = this.ctrl.getGraphe();
		Viewer viewer = new Viewer(graphe, ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        viewer.enableAutoLayout();

        View view = viewer.addDefaultView(false);

        this.add(view, BorderLayout.CENTER);

		this.setVisible(true);
	}
}
