package pt.clubedohardware.characters.doctor;

import pt.clubedohardware.characters.patient.IResponder;
import pt.clubedohardware.dataorganizer.DataOrganizer;
import pt.clubedohardware.dataset.*;
import pt.clubedohardware.dialoguecreator.IDialogue;
import pt.clubedohardware.node.Tree;

/**
 *
 * @author leonardolivraremartins
 */
public class Doctor implements IDoctor{
    private String name;
    private String diagnostic;
	
    private String[] attributes;
    private Tree tree;
    private IResponder paciente;
    private ITableProducer producer;
    
    
    public void setTree(Tree tree) {
    	this.tree = tree;
    }
    
    public void connect(ITableProducer producer) {
        this.producer = producer;
    }
  
    public Doctor(String name) {
		this.name = name;
	}

    
    public void connect(IResponder paciente) {
        this.paciente = paciente;
    }
    
    public void startInterview(IDialogue dialogue) {
       
        
		DataOrganizer dataOr = new DataOrganizer();
        
    }
}

