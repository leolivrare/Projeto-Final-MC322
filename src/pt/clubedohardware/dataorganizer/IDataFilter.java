package pt.clubedohardware.dataorganizer;

import java.util.List;

/**
 *DataFilter tem como funcao filtrar os dados da tabela, retornando um vetor das doencas unicas da tabela
 *e uma matriz de frequencia de sintomas, que serao utilizados na construcao da arvore.
 *
 *@author SerodioJ
*/

public interface IDataFilter {
    List<String> diseaseFilter(String[][] instances);
    int[][] symptomFilter(String[][] instances, List<String> diseases);
}
