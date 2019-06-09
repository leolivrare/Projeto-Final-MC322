package pt.clubedohardware.dialoguecreator;

import java.util.ArrayList;

public class DialogueCreator implements IDialogue{
	private ArrayList<String> falas;
	private ArrayList<String> personagem;
	private int index;
	
	
	public DialogueCreator(int index) {
		this.index = index;
		falas = new ArrayList<String>();
		personagem = new ArrayList<String>();
	}
	
	public ArrayList<String> getFalas() {
		return falas;
	}

	public ArrayList<String> getPersonagem() {
		return personagem;
	}

	public void addDoctorSpeech(String speech) {
		falas.add(index, speech);
		personagem.add(index, "Doctor");
		index++;
	}
	
	public void addPatientSpeech(String speech) {
		falas.add(index, speech);
		personagem.add(index, "Pacient");
		index++;
	}

}
