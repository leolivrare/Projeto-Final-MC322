package pt.clubedohardware.dataset;

/**Interface de refer�ncia feita pelo professor Andr� Santanch�
 * 
 * @author santanche
 */

public interface ITableProducer {
	  String[] requestAttributes();
	  String[][] requestInstances();
	  void setAttributes(String[] attributes);
}