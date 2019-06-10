package pt.clubedohardware.Path;

import java.io.File;

import javax.swing.JFileChooser;

public class Path {
	
	public static String getPath(String arq) {
		String pathCSV = null;
		JFileChooser file = new JFileChooser("../projetoFinal/src");
        while (pathCSV == null || !(pathCSV.contains(arq))){
            int returnValue = file.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = file.getSelectedFile();
                pathCSV = selectedFile.getAbsolutePath();
            }
        }
        return pathCSV;
	}
	
	public static String getPath(String arq1, String arq2) {
		String path = null;
        JFileChooser file = new JFileChooser("../projetoFinal/src");
        while (path == null || !(path.contains(arq1) || path.contains(arq2))){
            int returnValue = file.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = file.getSelectedFile();
                path = selectedFile.getAbsolutePath();
            }
        }
        return path;
	}
	
	public static String getFolderName() {
		String path = null;
		JFileChooser file = new JFileChooser("../SerializedData");
        int returnValue = file.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = file.getSelectedFile();
            path = selectedFile.getName();
        }
        return path;
	}
}
