package pt.clubedohardware.characters.patient;

import pt.clubedohardware.dialoguecreator.IDialogue;

/**
 *
 * @author leonardolivraremartins
 */
public interface IResponder {
    public String ask(String question, IDialogue dialogue);
    public String getName();
}
