package pt.clubedohardware.dataorganizer;

import pt.clubedohardware.node.*;
import java.util.List;

/**
 *ITreeMaker tem como funcao construir uma arvore binaria a partir dos dados filtrados da tabela.
 *
 *@author SerodioJ
 */

public interface ITreeMaker{
	Tree treeMaker(List<String> diseases, int[][] symptomFrequency, String[][] instances);
}