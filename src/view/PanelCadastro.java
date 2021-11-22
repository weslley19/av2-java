
package view;

import beans.Aluno;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import model.AlunoTableModel;
import util.FileManager;

public class PanelCadastro extends JPanel{
    
    private final JFrame window;
    private final AlunoTableModel modelAlunos;
    
    
    public PanelCadastro(JFrame window, AlunoTableModel model) {
        this.window = window;
        this.modelAlunos = model;
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        initComponentes();
        
    }
    
    private void initComponentes() {
        
        JButton cadastrar = new JButton("Cadastar");
        
        cadastrar.addActionListener((ActionEvent e) -> {
            JDialog formulario = new Formulario(
                    PanelCadastro.this.window,
                    "Cadastrar Aluno",
                    this.modelAlunos
            );
            formulario.pack();
            formulario.setVisible(true);
        });
        
        JButton salvar = new JButton("Salvar Arquivo");
        salvar.addActionListener((evento) -> {
            try {
                FileManager.salvar(modelAlunos.getAlunos());
                System.out.println("Salvou");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao salvar!",
                        "Erro",
                       JOptionPane.ERROR_MESSAGE
                );
            }
        });
        
        JButton maiorAluno = new JButton("Buscar aluno mais velho");
        maiorAluno.addActionListener((evento) -> {
            Aluno aluno = buscarAlunoMaisVelho();
            if(aluno == null) {
                JOptionPane.showMessageDialog(null,
                        "Nenhum aluno cadastrado!",
                        "Erro!",
                       JOptionPane.ERROR_MESSAGE
                );
            } else {
                Exibicao exibicao = new Exibicao(aluno, null);
                exibicao.pack();
                exibicao.setVisible(true);
            }
        });
        
        JButton menorAluno = new JButton("Buscar aluno mais jovem");
        menorAluno.addActionListener((evento) -> {
            Aluno aluno = buscarAlunoMaisNovo();
            if(aluno == null) {
                JOptionPane.showMessageDialog(null,
                        "Nenhum aluno cadastrado!",
                        "Erro!",
                       JOptionPane.ERROR_MESSAGE
                );
            } else {
                Exibicao exibicao = new Exibicao(aluno, null);
                exibicao.pack();
                exibicao.setVisible(true);
            }
        });
        
        this.add(cadastrar);  
        this.add(salvar);
        this.add(maiorAluno);
        this.add(menorAluno);
    }
    
    private Aluno buscarAlunoMaisVelho() {
        List<Aluno> lista = modelAlunos.getAlunos();
        Aluno aluno = null;
        
        for(Aluno a : lista) {
            if(aluno == null || a.getIdade() > aluno.getIdade())
                aluno = a;
        }
        
        return aluno;
    }
    
    private Aluno buscarAlunoMaisNovo() {
        List<Aluno> lista = modelAlunos.getAlunos();
        Aluno aluno = null;
        
        for(Aluno a : lista) {
            if(aluno == null || a.getIdade() < aluno.getIdade())
                aluno = a;
        }
        
        return aluno;
    }
}
