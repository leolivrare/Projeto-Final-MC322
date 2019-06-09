package pt.clubedohardware.node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ArrayDeque;
import java.util.Deque;
import java.io.Serializable;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;

/**
 *Arvore
 *
 *@author SerodioJ
 */

public class Tree implements Serializable{
	private Node root;
	private List<Integer> keySymptoms;
	private List<String> diseases;
	private List<Integer> priority;

	public Tree(Node root, List<Integer> keySymptoms, List<String> diseases, List<Integer> priority){
		this.root = root;
		this.keySymptoms = keySymptoms;
		this.diseases = diseases;
		this.priority = priority;
	}
	
	//Gets e sets
	public Node getRoot() {
		return this.root;
	}
	
	public List<Integer> getKey(){
		return this.keySymptoms;
	}
	
	public List<String> getDiseases() {
		return this.diseases;
	}
	
	public List<Integer> getPriority() {
		return this.priority;
	}
	
	public void setRoot(Node root) {
		this.root = root;
	}

	//Transforma a arvore em um heap
	@SuppressWarnings("WeakerAccess")
	public List<Node> toHeap() {
		List<Node> heap = new ArrayList<>();
		Deque<Node> fila = new ArrayDeque<>();
		fila.add(this.root);
		while (!fila.isEmpty()) {
			Node atual = fila.removeFirst();
			heap.add(atual);
			if (atual.getEsquerdo() != null) fila.add(atual.getEsquerdo());
			if (atual.getDireito() != null) fila.add(atual.getDireito());

		}
		return heap;
	}

	//Metodo de serializacao DAO para verificar o estado da arvore
	public void DAO(String fileName, String dirName){
		try{
			File dir = new File(dirName);
			FileWriter arquivo = new FileWriter(new File(dir, fileName));
			PrintWriter escritor = new PrintWriter(arquivo);
			escritor.println("@Tree");
			escritor.println("Sintomas unicos: " + this.keySymptoms);
			escritor.println("Doencas: "+ this.diseases);
			escritor.println("Prioridade: " + this.priority);
			escritor.println("Nos da Arvore");
			List<Node> heap = this.toHeap();
			int no = 0;
			for(Node node : heap) {
				node.DAO(escritor, no);
				no++;
			}
			int diagnosticLeaves = 0, filledLeaves = 0, filled1 = 0, filled2 = 0, filled3 = 0, filledplus = 0, fakedFilled = 0;
			for (int i = (heap.size()-1)/2; i < heap.size(); i++) {
				if (!heap.get(i).getDiseases().isEmpty())
					diagnosticLeaves++;
				if(heap.get(i).getFilled() && !heap.get(i).getDiseases().isEmpty()) {
					filledLeaves++;
					if (heap.get(i).getFilledPower() == 1)
						filled1++;
					else if (heap.get(i).getFilledPower() == 2)
						filled2++;
					else if (heap.get(i).getFilledPower() == 3)
						filled3++;
					else if (heap.get(i).getFilledPower() < 10)
						filledplus++;
					else
						fakedFilled++;
				}
			}
			escritor.println("Folhas com diagnostico: "+ diagnosticLeaves+"/"+(heap.size()+1)/2);
			escritor.println("Folhas preechidas pelo DiagnosticCompleter: "+filledLeaves+"/"+(heap.size()+1)/2);
			escritor.println("Folhas preechidas de grau 1: "+filled1+"/"+filledLeaves);
			escritor.println("Folhas preechidas de grau 2: "+filled2+"/"+filledLeaves);
			escritor.println("Folhas preechidas de grau 3: "+filled3+"/"+filledLeaves);
			escritor.println("Folhas preechidas de grau maior que 4 e menor que 10: "+filledplus+"/"+filledLeaves);
			escritor.println("Folhas colocadas como nothing default ou com diagnostico inconclusivo: "+fakedFilled+"/"+filledLeaves);
			arquivo.close();
			System.out.println("Gravacao realizada com sucesso");
		} catch(IOException erro){
			System.out.println("Erro de gravacao");
		}
	}
}

