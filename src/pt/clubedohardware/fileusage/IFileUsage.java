package pt.clubedohardware.fileusage;

import java.util.List;
import pt.clubedohardware.node.Tree;

/**
 *FileUsage salva os dados importantes do programa em execucao em um arquivo texto,
 *a fim de que os dados nao sejam processados novamente.
 *O componente pode ser adaptado para casos particulares.
 *
 *@author SerodioJ
 */

public interface IFileUsage{
	void save(List<String> diseases, int[][] symptomFrequency, Tree tree, String pathCsv);
	void save(Tree tree);
	List<String> getDiseases();
	int[][] getFrequency();
	Tree getTree();
	String getPathCSV();
}