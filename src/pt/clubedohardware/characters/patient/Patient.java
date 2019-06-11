package pt.clubedohardware.characters.patient;

import pt.clubedohardware.dataset.*;
import pt.clubedohardware.dialoguecreator.IDialogue;

/**
 *
 * @author leonardolivraremartins
 */
public class Patient implements IPatient{
    private int ramdomNumber;
    
    private ITableProducer producer;
    
    private String attributes[];
    private String patientInstance[];
    
    private String name;
    
    public String getName() {
    	return name;
    }
    
    public Patient(String name) {
    	this.name = name;
    }
    
    public void connect(ITableProducer producer) {
        this.producer = producer;

        attributes = producer.requestAttributes();
        String instances[][] = producer.requestInstances();

        ramdomNumber = (int)(Math.random() * instances.length);
        patientInstance = instances[ramdomNumber];
        System.out.println("Patient has "+patientInstance[patientInstance.length-1]);
        System.out.println();
        
    }
    
    public String ask(String question, IDialogue dialogue) {
        String result = "Nao sei";
        
        for(int i=0; i<(attributes.length); i++) {
            if (question.equalsIgnoreCase(attributes[i])) {
                result = (patientInstance[i].equals("1") || patientInstance[i].equals("t")) ? "Sim" : "Nao";
                break;
            }
        }
        return result;
    }
    
    @Override
    public String getRealDisease() {
    	return this.patientInstance[this.patientInstance.length-1];
    }
}
