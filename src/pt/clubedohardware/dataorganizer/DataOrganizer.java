package pt.clubedohardware.dataorganizer;

import pt.clubedohardware.node.*;
import pt.clubedohardware.diagnosticcompleter.DiagnosticCompleter;
import java.util.ArrayList;
import java.util.List;

/**
 *Classe que analisa os dados da matriz e constroe uma arvore.
 *
 *@author SerodioJ,leonardolivraremartins.
 */

public class DataOrganizer implements ITreeMaker, IDataFilter {

	/* Implementacao de IDataFilter */
	//Retorna as doencas que ocorrem na matriz
	@Override
	public List<String> diseaseFilter(String[][] instances) {
		List<String> diseases = new ArrayList<>();
		int indexDisease;

		for (String[] instance : instances) {
			indexDisease = (instance.length) - 1;
			if (diseases.indexOf(instance[indexDisease]) == -1) {
				diseases.add(instance[indexDisease]);
			}
		}

		return diseases;
	}

	//Retorna uma matriz de frequencia dos sintomas em relacao as doencas
	@Override
	public int[][] symptomFilter(String[][] instances, List<String> diseases) {
		int[][] frequencyMatrix = new int[instances[0].length - 1][diseases.size() + 1];
		int index, diseaseIndex = instances[0].length-1;

		for (int j=0; j<(instances[0].length-1); j++)
			for (String[] instance : instances)
				if (instance[j].equals("1") || instance[j].equals("t")) {
					index = diseases.indexOf(instance[diseaseIndex]);
					frequencyMatrix[j][index] += 1;
					frequencyMatrix[j][diseases.size()] += 1;
				}

		return frequencyMatrix;
	}



	/*Implementacao de ITreeMaker*/
	//Metodo de construcao da arvore
	@Override
	public Tree treeMaker(List<String> diseases, int[][] symptomFrequency, String[][] instances) {
		NodeCreator nC = new NodeCreator();
		List<Integer> keySymptoms = unique(symptomFrequency);
        int[] path = new int[symptomFrequency.length];
        for (int i = 0; i < path.length; i++)
        	if (keySymptoms.indexOf(i) == -1)
        		path[i] = -1;
        List<Integer> priorities = priority(symptomFrequency, keySymptoms);
		Tree diagnosticTree = new Tree(treeSkeleton(priorities.size(), 0, -1, nC, priorities, path, false), keySymptoms, diseases, priorities);
		diagnosticTree.setRoot(treeRunner(diagnosticTree.getRoot(), diagnosticTree, instances));
		return diagnosticTree;
	}

	//Retorna uma lista de sintomas caracteristicos de uma doenca
	private List<Integer> unique(int[][] symptomFrequency) {
		List<Integer> unique = new ArrayList<>();
		for (int i = 0; i < symptomFrequency.length; i++) {
			for (int j = 0; j < symptomFrequency[0].length-1; j++) {
				if (symptomFrequency[i][j] == 0)
					continue;
				if (symptomFrequency[i][j] == symptomFrequency[i][symptomFrequency[0].length - 1]) {
					unique.add(i);
					break;
				}
			}
		}
		return unique;
	}

	//Compara dois vetores, sendo um deles de inteiros e o outro de String.
	private boolean comparePath(int[] path1, String[] path2){
		boolean equal = true;
		int tradutor;
		for (int i = 0; i < path1.length; i++){
			if (path2[i].equals("1") || path2[i].equals("t"))
				tradutor = 1;
			else
				tradutor = 0;
			if (tradutor != path1[i])
				equal = false;
		}
		return equal;
	}

	//Metodo recursivo para construir a arvore de diagnostico.
	private Node treeSkeleton(int symptoms, int current, int previous, NodeCreator nC, List<Integer> priority, int[] path, boolean esq) {
		if (current == symptoms)
			return nC.createNode(true, path, esq, previous);
		Node node = nC.createNode(priority.get(current), path, esq, previous);
		node.setDireito(treeSkeleton(symptoms, current+1, priority.get(current), nC, priority, node.getPath(), false));
		node.setEsquerdo(treeSkeleton(symptoms, current+1, priority.get(current), nC, priority, node.getPath(), true));
		return node;
	}

	//Retorna um vetor de ordem de prioridade para os sintomas na hora da busca na tabela.
	private List<Integer> priority(int[][] symptomFrequency, List<Integer> keySymptons) {
		List<Integer> priorities = new ArrayList<>();
		int indexSmaller = -1;
		for (int n = 0; n < (symptomFrequency.length - keySymptons.size()); n++) {
			for (int i = 0; i < symptomFrequency.length; i++) {
				if (keySymptons.indexOf(i) != -1 || priorities.indexOf(i) != -1)
					continue;
				if (indexSmaller == -1) {
					indexSmaller = i;
					continue;
				}
				if (symptomFrequency[i][symptomFrequency[0].length-1] < symptomFrequency[indexSmaller][symptomFrequency[0].length-1])
					indexSmaller = i;
			}
			priorities.add(indexSmaller);
			indexSmaller = -1;
		}
		return priorities;
	}

	//Metodo para comparar o caminho da arvore com a tabela de referencia. Usando o vetor priority para fazer o menor numero de comparacoes.
	private List<Integer> diagnostic(Tree tree, Node current, String[][] instances){
		DiagnosticCompleter DC = new DiagnosticCompleter();
		List<Integer> diagnostic = new ArrayList<>();
		for (String[] instance : instances)
			if (comparePath(current.getPath(), instance) && diagnostic.indexOf(tree.getDiseases().indexOf(instance[instances[0].length-1])) == -1)
				diagnostic.add(tree.getDiseases().indexOf(instance[instances[0].length-1]));
		if (diagnostic.isEmpty()){
			current.setFilled(true);
			diagnostic = DC.dataFiller(current, tree);
		}
		return diagnostic;

	}

	//Percorre a arvore colocando os diagnosticos nos nos-folha
	private Node treeRunner (Node current, Tree tree, String[][] instances) {
		if (current.getDiagnostico()) {
			current.setDiseases(diagnostic(tree, current, instances));
			return current;
		}
		current.setDireito(treeRunner(current.getDireito(), tree, instances));
		current.setEsquerdo(treeRunner(current.getEsquerdo(), tree, instances));
		return current;
	}
}