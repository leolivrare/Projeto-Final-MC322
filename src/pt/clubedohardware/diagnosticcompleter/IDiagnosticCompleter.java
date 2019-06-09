package pt.clubedohardware.diagnosticcompleter;

import pt.clubedohardware.node.*;
import java.util.List;

/**
 *DiagnosticCompleter tem como funcao completar os nos-folha de uma arvore de diagnostico
 *que nao possuem diagnostico.
 *
 *@author SerodioJ
 */

public interface IDiagnosticCompleter{
	List<Integer> dataFiller(Node node, Tree tree);
}