package pt.clubedohardware.Path;

import java.io.File;

import javax.swing.JFileChooser;

public class Path {
	
	public static String getPath(String csv) {
		String pathCsv = null;
		JFileChooser file = new JFileChooser("../projetoFinal/src");
        while (pathCsv == null || !(pathCsv.contains(".csv"))){
            int returnValue = file.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = file.getSelectedFile();
                pathCsv = selectedFile.getAbsolutePath();
            }
        }
        return pathCsv;
	}
	
	public static String getPath(String csv, String txt) {
		String path = null;
        JFileChooser file = new JFileChooser("../projetoFinal/src");
        while (path == null || !(path.contains(".csv") || path.contains(".txt"))){
            int returnValue = file.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = file.getSelectedFile();
                path = selectedFile.getAbsolutePath();
            }
        }
        return path;
	}
}
