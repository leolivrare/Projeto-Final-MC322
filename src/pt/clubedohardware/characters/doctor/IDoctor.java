package pt.clubedohardware.characters.doctor;

import pt.clubedohardware.dataset.*;
import pt.clubedohardware.node.Tree;

/**
 *
 * @author leonardolivraremartins
 */
public interface IDoctor extends IEnquirer, IResponderReceptacle, ITableProducerReceptacle {
	public void setTree(Tree tree);
	public void resetDiagnostic();
}
