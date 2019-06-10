package pt.clubedohardware.fileusage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import pt.clubedohardware.node.Tree;

/**
 *FileUsage seraliza a arvore junto com os nos.
 *
 * @author leonardolivraremartins
 */

public class FileUsage implements IFileUsage{
    
    /*Serializa a lista de doencas(diseases), a matriz de frequencia(symptomFrequency)
     *e a arvore de diagnostico(tree)*/
    @Override
    public void save(List<String> diseases, int[][] symptomFrequency, Tree tree, String pathCSV) {
    	String[] auxiliar = pathCSV.split("/");
    	String customFolderName = auxiliar[auxiliar.length-1].replace(".csv", "");
        //Cria um diretorio chamado SerializedData na pasta do projeto
        boolean ok = new java.io.File("../", "SerializedData/"+customFolderName).mkdirs();
        
        serialize(diseases, "diseases.txt", customFolderName);
        serialize(symptomFrequency, "symptomFrequency.txt", customFolderName);
        serialize(tree, "tree.txt", customFolderName);
        serialize(pathCSV, "pathCSV.txt", customFolderName);
    }
    
    //Serializa somente tree (arvore de diagnosticos)
    @Override
    public void save(Tree tree, String pathCSV) {
    	String[] auxiliar = pathCSV.split("/");
    	String customFolderName = auxiliar[auxiliar.length-1].replace(".csv", "");
    	boolean ok = new java.io.File("../", "SerializedData/"+customFolderName).mkdirs();
        serialize(tree, "tree.txt", customFolderName);
    }

    //Deserializa a lista de doencas
    @Override
    @SuppressWarnings("unchecked")
    public List<String> getDiseases(String folderName) {
        List<String> diseases;
        diseases = (ArrayList<String>) deserialize("diseases.txt", folderName);
        
        return diseases;
    }
    
    public String getPathCSV(String folderName) {
    	String pathCSV;
    	pathCSV = (String) deserialize("pathCSV.txt", folderName);
    	
    	return pathCSV;
    }

    //Deserializa a matriz de frequencia
    @Override
    public int[][] getFrequency(String folderName) {
        int[][] symptomFrequency;
        symptomFrequency = (int[][]) deserialize("symptomFrequency.txt", folderName);
        
        return symptomFrequency;
    }

    //Deserializa a arvore de diagnosticos
    @Override
    public Tree getTree(String folderName) {
        Tree tree;
        tree = (Tree) deserialize("tree.txt", folderName);
        
        return tree;
    }
    
    //Metodo de serializacao utilizando o recurso padrao do Java
    private void serialize(Object obj, String arqName, String folderName) {
        File arq = new File("../serializedData/"+folderName+"/"+arqName);
        try {
            arq.delete();
            arq.createNewFile();
            
            ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
            objOutput.writeObject(obj);
            objOutput.close();
        } catch (IOException erro) {
            System.out.printf("Erro: %s", erro.getMessage());
        }
    }
    
    //Metodo de deserializacao utilizando o recurso padrao do Java
    private Object deserialize(String arqName, String folderName) {
        Object obj = null;
        try {
            File arq = new File("../serializedData/"+folderName+"/"+arqName);
            
            if (arq.exists()) {
                ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
                obj = objInput.readObject();
                objInput.close();
        }
        } catch(IOException erro1) {
          System.out.printf("Erro: %s", erro1.getMessage());
        } catch(ClassNotFoundException erro2) {
          System.out.printf("Erro: %s", erro2.getMessage());
        }
        return obj;
    }
}
