package pt.clubedohardware.dialoguecreator;

import java.util.ArrayList;

public class DialogueCreator implements IDialogue{
	private ArrayList<String> falas;
	private ArrayList<String> personagem;
	private int index;
	
	public DialogueCreator() {
		index = 0;
	}
	
	public ArrayList<String> getFalas() {
		return falas;
	}

	public ArrayList<String> getPersonagem() {
		return personagem;
	}

	public void addDoctorSpeech(String speech) {
		falas.add(index, speech);
		personagem.add(index, speech);
		index++;
	}
	
	public void addPatientSpeech(String speech) {
		falas.add(index, speech);
		personagem.add(index, speech);
		index++;
	}

}
