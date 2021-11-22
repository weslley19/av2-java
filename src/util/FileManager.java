
package util;

import beans.Aluno;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileManager {
    
    public static void salvar(List<Aluno> alunos) throws IOException {
        FileFilter filter = new FileNameExtensionFilter("Arquivos txt", ".txt");
        JFileChooser fileChooser = new JFileChooser("alunos.txt");
        fileChooser.setFileFilter(filter);
        fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        
 
        int userSelection = fileChooser.showSaveDialog(null);
 
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            FileWriter arq = new FileWriter(
                    fileToSave.getAbsolutePath()+".txt"
            );
            PrintWriter gravaArq = new PrintWriter(arq);
            salvarArquivo(gravaArq, alunos);
        }
        
    }
    
    private static void salvarArquivo(PrintWriter gravaArq, List<Aluno> alunos) {
        for(Aluno aluno: alunos) {
            gravaArq.printf("%d,%s,%d,%s,%s,%s",
                    aluno.getMatricula(),
                    aluno.getNome(),
                    aluno.getIdade(),
                    aluno.getDataNascimento(),
                    aluno.getTelefone(),
                    aluno.getCpf()
            );
            gravaArq.println();
        }
        
        gravaArq.close();
    }
}
