package pt.clubedohardware.characters.patient;

import pt.clubedohardware.dataset.*;

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
        
    }
    
    public String ask(String question) {
        String result = "unknown";
        
        for(int i=0; i<(attributes.length); i++) {
            if (question.equalsIgnoreCase(attributes[i])) {
                result = (patientInstance[i].equals("1")) ? "1" : "0";
                break;
            }
        }
        return result;
    }
}
