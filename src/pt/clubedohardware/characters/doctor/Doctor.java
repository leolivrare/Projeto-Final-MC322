package pt.clubedohardware.characters.doctor;

import java.util.ArrayList;
import java.util.List;

import pt.clubedohardware.characters.patient.IResponder;
import pt.clubedohardware.dataorganizer.DataOrganizer;
import pt.clubedohardware.dataset.*;
import pt.clubedohardware.dialoguecreator.IDialogue;
import pt.clubedohardware.node.Node;
import pt.clubedohardware.node.Tree;

/**
 *
 * @author leonardolivraremartins
 */
public class Doctor implements IDoctor{
    private String name;
    private String diagnostic;
	
    private Tree tree;
    private IResponder patient;
    private ITableProducer producer;
    
    
    public void setTree(Tree tree) {
    	this.tree = tree;
    }
    
    public void connect(ITableProducer producer) {
        this.producer = producer;
    }
  
    public Doctor(String name) {
		this.name = name;
		diagnostic = "";
	}

    
    public void connect(IResponder patient) {
        this.patient = patient;
    }
    
    public void resetDiagnostic() {
    	diagnostic = "";
    }
    
    public void startInterview(IDialogue dialogue) {
    	String[] attributes = producer.requestAttributes();	
        List<String> diseases = tree.getDiseases();
    	String answer;
    	boolean verificador = false;
    	
    	dialogue.addPatientSpeech("Oi, "+name);
    	dialogue.addPatientSpeech("Oi, "+name+"! Nao estou me sentindo bem...");
    	dialogue.addDoctorSpeech("Ola, "+patient.getName());
    	dialogue.addDoctorSpeech("Irei te examinar para ver como posso te ajudar!");
    	dialogue.addPatientSpeech("Ok!");
    	
        Node node = tree.getRoot();
        
        if (!(tree.getKeySymptoms().isEmpty())) {
        	for(Integer x : tree.getKeySymptoms()) {
        		dialogue.addDoctorSpeech(attributes[x]);
            	answer = patient.ask(attributes[x], dialogue);
            	dialogue.addPatientSpeech(answer);
            	
            	if (answer.equals("Sim")) {
            		diagnostic = diagnostic + diseases.get(tree.getKSDiagnostic(x)) + " ";
            		verificador  = true;
            	}
        	}
        }
        
        if (!verificador) {
	        while (!(node.getDiagnostico())) {
	        	dialogue.addDoctorSpeech(attributes[node.getSymptom()]);
	        	answer = patient.ask(attributes[node.getSymptom()], dialogue);
	        	dialogue.addPatientSpeech(answer);
	        	if(answer.equals("Sim")) {
	        		node = node.getEsquerdo();
	        	}
	        	else {
	        		node = node.getDireito();
	        	}
	        }
	        
	        List<Integer> patientDiseases = node.getDiseases();
	        for(Integer x : patientDiseases) {
	        	diagnostic = diagnostic + diseases.get(x) + " ";
	        }
        }
        
        dialogue.addDoctorSpeech("Infelizmente voce esta com "+diagnostic);
    }
}

