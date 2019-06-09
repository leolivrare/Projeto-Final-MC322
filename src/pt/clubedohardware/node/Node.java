package pt.clubedohardware.node;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.io.PrintWriter;

/**
 *No da arvore
 *
 *@author SerodioJ
 */

public class Node implements Serializable{
	private Node esquerdo, direito;
	private int symptom, filledPower;
	private boolean diagnostico, filled;
	private List<Integer> diseases;
	private int[] path;
	
	Node(int symptom, int[] path, boolean esq, int previous){
		this.symptom = symptom;
		if(previous == -1)
		    this.path = path;
		else{
		    this.path = path.clone();
		    if (esq)
		        this.path[previous] = 1;
		    else
		        this.path[previous] = 0;
        }
	}
	
	Node (boolean diagnostico, int[] path, boolean esq, int previous){
	    this.diagnostico = diagnostico;
        if(previous == -1)
            this.path = path;
        else{
            this.path = path.clone();
            if (esq)
                this.path[previous] = 1;
            else
                this.path[previous] = 0;
        }
	}
	
	//Gets e sets
	public Node getEsquerdo(){
		return this.esquerdo;
	}

	public Node getDireito(){
		return this.direito;
	}

	public int getSymptom(){
		return this.symptom;
	}

	public int getFilledPower(){
		return this.filledPower;
	}

	public boolean getDiagnostico(){
		return this.diagnostico;
	}

	public boolean getFilled(){
		return this.filled;
	}

	public int[] getPath(){ return this.path; }

	public List<Integer> getDiseases(){
		return this.diseases;
	}

	public void setEsquerdo(Node esquerdo){
		this.esquerdo = esquerdo;
	}
 
	public void setDireito(Node direito){
		this.direito = direito;
	}

	public void setSymptom(int symptom){
		this.symptom = symptom;
	}

	public void setDiagnostico(boolean diagnostico){
		this.diagnostico = diagnostico;
	}

	public void setFilled(boolean filled){this.filled = filled;}
	
	public void setDiseases(List<Integer> diseases) {
		this.diseases = diseases;
	}

	public void setPath(int[] path) { this.path = path; }

	//Aumenta o grau de completamento no DiagnosticCompleter
	public void increaseFilledPower(){
		this.filledPower++;
	}

	//Metodo de serializacao DAO para verificar o estado do no
	public void DAO(PrintWriter escritor, int numero){
		escritor.println("@Node "+ numero);
		escritor.println("Sintoma: " + this.symptom);
		escritor.println("Diagnostico: " + this.diagnostico);
		escritor.println("Filled: " + this.filled);
		escritor.println("FilledPower: " + this.filledPower);
		escritor.println("Doencas: "+ this.diseases);
		List<Integer> caminho = new ArrayList<>();
		for(int teste:this.path){
			caminho.add(teste);
		}
		escritor.println("Path: " + caminho);
		escritor.println("");
	}
}
