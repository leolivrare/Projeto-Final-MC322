package pt.clubedohardware.diagnosticcompleter;

/**
 * DiagnosticCompleter completa os nos-folha que nao possuem diagnostico
 *
 * @author SerodioJ
 */

import pt.clubedohardware.node.*;
import java.util.ArrayList;
import java.util.List;

public class DiagnosticCompleter implements IDiagnosticCompleter {
    //Metodo que preenche o diagnostico vazio
    @Override
    public List<Integer> dataFiller(Node node, Tree tree){
        List<Integer> data = new ArrayList<>();
        int[] path = node.getPath();
        List<Integer> pinned = new ArrayList<>();
        while (data.isEmpty()){
            node.increaseFilledPower();
            if (node.getFilledPower() >= 10) {
                boolean ind = true;
                for (int value : path)
                    if (value != 0)
                        ind = false;
                if (ind)
                    data.add(-1);
                else
                    data.add(-2);
                break;
            }
            superFiller(tree.getRoot(), path, tree.getPriority(), data, node.getFilledPower(),pinned);
        }
        return data;
    }

    //Metodo que percorre uma arvore a partir de um caminho dado
    private void runTree(Node root, int[] path, List<Integer> data, List<Integer> priority){
        Node current = root;
        for (int value : priority){
            if (path[value] == 1)
                current = current.getEsquerdo();
            else
                current = current.getDireito();
        }
        if (!(current.getFilled()  || current.getDiseases() == null || current.getDiseases().size() == 0))
            for (int disease : current.getDiseases()) {
                if (!data.contains(disease))
                    data.add(disease);
            }
    }

    //Metodo que troca uma posicao da path
    private void change(int[] path, int index){
        if (path[index] == 0)
            path[index] = 1;
        else
            path[index] = 0;
    }

    //Metodo dataFiller com grau maior de alteracao na path
    @SuppressWarnings("all")
    private void superFiller( Node root, int[] path, List<Integer> priority, List<Integer> data, int power, List<Integer> pinned){
        if (power == 1){
            if (pinned.isEmpty())
                for (int i = 0; i < priority.size(); i++) {
                    change(path, priority.get(i));
                    runTree(root, path, data, priority);
                    change(path, priority.get(i));
                }
            else
                for(int i = pinned.get(pinned.size()-1)+1; i < priority.size(); i++){
                    change(path, priority.get(i));
                    runTree(root, path, data, priority);
                    change(path, priority.get(i));
                }
        } else {
            if (pinned.isEmpty())
                for (int i = 0; i < priority.size(); i++) {
                    pinned.add(i);
                    change(path, priority.get(i));
                    superFiller(root, path, priority, data, power - 1, pinned);
                    change(path, priority.get(i));
                    pinned.clear();
                }
            else
                for (int i = pinned.get(pinned.size()-1)+1; i < priority.size(); i++) {
                    pinned.add(i);
                    change(path, priority.get(i));
                    superFiller(root, path, priority, data, power - 1, pinned);
                    change(path, priority.get(i));
                    pinned.remove(pinned.indexOf(i));
                }
        }
    }

}
