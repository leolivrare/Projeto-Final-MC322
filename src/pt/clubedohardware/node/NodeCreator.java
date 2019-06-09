package pt.clubedohardware.node;

/**
 *Fabrica de nos
 *
 *@author SerodioJ
 */

public class NodeCreator{

	public Node createNode(int symptom, int[] path, boolean esq, int previous){
		return new Node(symptom, path, esq, previous);
	}
	
	public Node createNode(boolean diagnostico, int[] path, boolean esq, int previous){
		return new Node(diagnostico, path, esq, previous);
	}
}