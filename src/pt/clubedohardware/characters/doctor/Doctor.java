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
    	String answer;
    	dialogue.addPatientSpeech("Hi, "+name);
    	dialogue.addPatientSpeech("Hi, "+name);
    	dialogue.addDoctorSpeech("Hi, "+patient.getName());
    	
        Node node = tree.getRoot();
        
        while (!(node.getDiagnostico())) {
        	dialogue.addDoctorSpeech(attributes[node.getSymptom()]);
        	answer = patient.ask(attributes[node.getSymptom()], dialogue);
        	dialogue.addPatientSpeech(answer);
        	if(answer.equals("1")) {
        		node = node.getEsquerdo();
        	}
        	else {
        		node = node.getDireito();
        	}
        }
        List<Integer> patientDiseases = node.getDiseases();
        List<String> diseases = tree.getDiseases();
        for(Integer x : patientDiseases) {
        	diagnostic = diagnostic + diseases.get(x);
        }
        
        dialogue.addDoctorSpeech(diagnostic);
    }
}

