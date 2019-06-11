package pt.clubedohardware.characters.doctor;

import java.util.ArrayList;
import java.util.List;

import jsmaiorjava.implementations.Tratamento;
import jsmaiorjava.interfaces.ITratamento;
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
    private String name, finalDiagnostic;
    private List<Integer> diagnosticAuxiliar;
	
    private Tree tree;
    private IResponder patient;
    private ITableProducer producer;
    
    
    public void setTree(Tree tree) {
    	this.tree = tree;
    }
    
    public String getFinalDiagnostic() {
    	return this.finalDiagnostic;
    }
    
    
    public String getName() {
    	return name;
    }
    
    public void connect(ITableProducer producer) {
        this.producer = producer;
    }
  
    public Doctor(String name) {
		this.name = name;
		diagnosticAuxiliar = new ArrayList<>();
	}

    
    public void connect(IResponder patient) {
        this.patient = patient;
    }
    
    public void resetDiagnostic() {
    	diagnosticAuxiliar.clear();
    }
    
    public String generateStringDiagnostic() {
    	String retorno = "";
    	for(int index : this.diagnosticAuxiliar) {
    		retorno = retorno + tree.getDiseases().get(index) + " ou "; 
    	}
    	retorno = retorno.substring(0, retorno.lastIndexOf(" ou "));
    	return retorno;
    }
    
    private void createFinalDiagnostic() {
    	this.finalDiagnostic = this.tree.getDiseases().get((int)Math.random() * this.diagnosticAuxiliar.size());
    }
    public void startInterview(IDialogue dialogue) {
    	String[] attributes = producer.requestAttributes();	
        List<String> diseases = tree.getDiseases();
    	String answer;
    	boolean verificador = false;
    	
    	dialogue.addPatientSpeech("Oi, "+name);
    	dialogue.addPatientSpeech("Oi, "+name+"! Nao estou me sentindo bem...");
    	dialogue.addDoctorSpeech("Ola, "+patient.getName());
    	dialogue.addDoctorSpeech("Irei te fazer algumas perguntas para te examinar, ok?");
    	dialogue.addPatientSpeech("Ok!");
    	
        Node node = tree.getRoot();
        
        if (!(tree.getKeySymptoms().isEmpty())) {
        	for(Integer x : tree.getKeySymptoms()) {
        		dialogue.addDoctorSpeech("Voce esta com "+attributes[x]+"?");
            	answer = patient.ask(attributes[x], dialogue);
            	dialogue.addPatientSpeech(answer+"!");
            	
            	if (answer.equals("Sim")) {
            		diagnosticAuxiliar.add(tree.getKSDiagnostic(x));
            		verificador  = true;
            	}
        	}
        }
        
        if (!verificador) {
	        while (!(node.getDiagnostico())) {
	        	dialogue.addDoctorSpeech("Voce esta com "+attributes[node.getSymptom()]+"?");
	        	answer = patient.ask(attributes[node.getSymptom()], dialogue);
	        	dialogue.addPatientSpeech(answer+"!");
	        	if(answer.equals("Sim")) {
	        		node = node.getEsquerdo();
	        	}
	        	else {
	        		node = node.getDireito();
	        	}
	        }
	        
	        diagnosticAuxiliar = node.getDiseases();
	       
        }
       createFinalDiagnostic();
        if (node.getFilled() && node.getFilledPower() <= 2) {
        	dialogue.addDoctorSpeech("Não tenho tanta certeza , mas acredito que você esteja com "+generateStringDiagnostic()+". Mais provavelmente "+this.finalDiagnostic);
        } else if (node.getFilled() && node.getFilledPower() > 2) {
        	dialogue.addDoctorSpeech("Estou muito na dúvida. Gostaria de pedir alguns exames,  mas acredito que você esteja com "+generateStringDiagnostic()+". Mais provavelmente "+this.finalDiagnostic);
        } else {
        	dialogue.addDoctorSpeech("Infelizmente voce esta com "+generateStringDiagnostic());
            dialogue.addDoctorSpeech("Acredito que seu quadro seja de "+this.finalDiagnostic);
        }
        
        ITratamento tratamento = new Tratamento(this.finalDiagnostic);
        dialogue.addDoctorSpeech("Mas fique tranquilo, basta voce "+tratamento.getTratamento()+" que ficara tudo bem!");
    }
}

