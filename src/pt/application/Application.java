package pt.application;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;

import jsmaiorjava.implementations.ImprimeAtestado;
import jsmaiorjava.implementations.Prontuario;
import jsmaiorjava.implementations.Tratamento;
import jsmaiorjava.implementations.ZumbiTwittero;
import jsmaiorjava.interfaces.IImprimeAtestado;
import jsmaiorjava.interfaces.IProntuario;
import jsmaiorjava.interfaces.ITratamento;
import pt.clubedohardware.Path.Path;
import pt.clubedohardware.characters.doctor.Doctor;
import pt.clubedohardware.characters.doctor.IDoctor;
import pt.clubedohardware.characters.patient.IPatient;
import pt.clubedohardware.characters.patient.Patient;
import pt.clubedohardware.dataorganizer.DataOrganizer;
import pt.clubedohardware.dataset.DataSetComponent;
import pt.clubedohardware.dialoguecreator.DialogueCreator;
import pt.clubedohardware.dialoguecreator.IDialogue;
import pt.clubedohardware.fileusage.FileUsage;
import pt.clubedohardware.fileusage.IFileUsage;
import pt.clubedohardware.node.Tree;
import pt.clubedohardware.userinterface.AnimationC;
import pt.clubedohardware.userinterface.IAnimationC;


public class Application {

	public static void main(String[] args) {
		boolean aux = true;
		Scanner sc = new Scanner(System.in);
		IAnimationC animation = new AnimationC();		
		DataOrganizer dataOr = new DataOrganizer();
		DataSetComponent dataset = new DataSetComponent();
		IFileUsage fileUsage = new FileUsage();
		Tree tree;
		int[][] symptomFrequency;
			
		IDoctor doctor = new Doctor("Doutor Variolla");
			
		animation.setDocName("Doutor Variolla");
		animation.setWindowName("Consultorio");
			
		//Deixa o usuario escolher o caminho da tabela ou da serializacao da arvore
	    String path = Path.getPath(".csv", ".txt");
			
		//Se ele escolher a tabela o programa ira construir a arvore, se ele escolher a arvore o jogo ira carregar ela
		if (path.contains(".csv")) {
			dataset.setDataSource(path);
			String[][] instances = dataset.requestInstances();
			    
		    List<String> diseases = dataOr.diseaseFilter(instances);
			symptomFrequency = dataOr.symptomFilter(instances, diseases);
			tree = dataOr.treeMaker(diseases, symptomFrequency, instances);
			    
			doctor.connect(dataset);
			doctor.setTree(tree);
		} else {
			String folderName =  Path.getFolderName();
			String pathCSV = fileUsage.getPathCSV(folderName);
			dataset.setDataSource(pathCSV);
			symptomFrequency = fileUsage.getFrequency(folderName);
				
			tree = fileUsage.getTree(folderName);
			doctor.connect(dataset);
			doctor.setTree(tree);
		}
		while(aux) {
			System.out.println("Deseja salvar os dados (S/N)?");
			Character c = sc.next().charAt(0);
			if (c.equals('S')) {
				fileUsage.save(tree.getDiseases(), symptomFrequency, tree, path);
				System.out.println("Dados salvos com sucesso!");
				aux = false;
			}
			else if (c.equals('N')) {
				aux = false;
			}
			else {
				System.out.println("Opcao invalida!");
			}
		}
		
		aux = true;
		
		while (aux) {	
			IDialogue dialogue = new DialogueCreator(0);
			
			System.out.print("Digite o nome do Paciente (somente o primeiro nome): ");
			String patientName = sc.next();
			System.out.println();
			
			//Instancia o paciente
			IPatient patient = new Patient(patientName);
			patient.connect(dataset);
			animation.setPacientName(patientName);
			
			//Conecta o doutor com o paciente
			doctor.connect(patient);
			doctor.startInterview(dialogue);
			
			ITratamento tratamento = new Tratamento(doctor.getDiagnostic());
			IProntuario prontuario = new Prontuario(tratamento, patient.getName(), doctor.getName());
			IImprimeAtestado atestado = new ImprimeAtestado(prontuario);
			atestado.imprime();
			
			ZumbiTwittero zt = new ZumbiTwittero(prontuario,
                    "GRANDE DIA:\n" +
                            doctor.getName()+" está cuidando de "+patient.getName()+", que maldosamente contraiu "+doctor.getDiagnostic()+".\n" +
                            "Pra não morrer terá que "+tratamento.getTratamento()+". \n ATÉ MAIS");
			
			zt.twittar();
			
			System.out.println();
			

			ArrayList<String> falasL = dialogue.getFalas();
			ArrayList<String> personagemL = dialogue.getPersonagem();
			
			String[] falas = new String[falasL.size()];
			String[] personagem = new String[personagemL.size()];
			
			for(int i=0; i<falasL.size(); i++) {
				falas[i] = falasL.get(i);
				personagem[i] = personagemL.get(i);
			}
			
			animation.story(falas, personagem);
			boolean aux2 = true;
			
			while(aux2) {
				System.out.println("Deseja analisar outro paciente (S/N)?");
				Character c = sc.next().charAt(0);
				if (c.equals('S')) {
					aux2 = false;
				}
				else if (c.equals('N')) {
					aux = false;
					aux2 = false;
					System.exit(0);
				}
				else {
					System.out.println("Opcao invalida!");
				}
			}
			
			doctor.resetDiagnostic();
		}
	}
}
