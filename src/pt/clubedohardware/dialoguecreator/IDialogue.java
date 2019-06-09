package pt.clubedohardware.dialoguecreator;

import java.util.ArrayList;

public interface IDialogue {
	
	public void addDoctorSpeech(String speech);
	public void addPatientSpeech(String speech);
	public ArrayList<String> getPersonagem();
	public ArrayList<String> getFalas();
}
