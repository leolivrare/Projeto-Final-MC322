package pt.clubedohardware.characters.patient;

import pt.clubedohardware.dataset.*;

/**
 *
 * @author leonardolivraremartins
 */
public interface IPatient extends IResponder, ITableProducerReceptacle {
	public String getRealDisease();
}
