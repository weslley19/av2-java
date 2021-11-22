package view;

import beans.Aluno;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class Exibicao extends JDialog {
    private Aluno aluno;
    private JFrame tela;
    
    
    public Exibicao (Aluno aluno, JFrame tela) {
        this.aluno = aluno;
        this.tela = tela;
        initComponentes();
        
        
    }
    
    private void initComponentes() {
        
        this.setSize(new Dimension(600, 500));
        this.setPreferredSize(new Dimension(600, 500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
        
        this.setTitle("Informações do Aluno");
        
        JSeparator s = new JSeparator();
        s.setOrientation(SwingConstants.HORIZONTAL);
        s.setMaximumSize(new Dimension(1200, 30));
        add(s);
         
        JLabel subtitulo = new JLabel("Dados do Aluno");
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        subtitulo.setMaximumSize(new Dimension(600, 30));
        subtitulo.setFont(new Font(Font.SERIF, Font.BOLD, 24));
        add(subtitulo);
        
        JSeparator s1 = new JSeparator();
        s1.setOrientation(SwingConstants.HORIZONTAL);
        s1.setMaximumSize(new Dimension(1200, 30));
        add(s1);
        
        JLabel matricula = new JLabel("Matricula: " + aluno.getMatricula());
        matricula.setHorizontalAlignment(SwingConstants.CENTER);
        matricula.setMaximumSize(new Dimension(600, 30));
        add(matricula);
        
        JLabel idade = new JLabel("Idade: " + aluno.getIdade());
        idade.setHorizontalAlignment(SwingConstants.CENTER);
        idade.setMaximumSize(new Dimension(600, 30));
         add(idade);
        
        JLabel nome = new JLabel("Nome: " + aluno.getNome());
        nome.setHorizontalAlignment(SwingConstants.CENTER);
        nome.setMaximumSize(new Dimension(600, 30));
        add(nome);
        
        JLabel cpf = new JLabel("Cpf: " + aluno.getCpf());
        cpf.setHorizontalAlignment(SwingConstants.CENTER);
        cpf.setMaximumSize(new Dimension(600, 30));
        add(cpf);
        
        JLabel telefone = new JLabel("Telefone: " + aluno.getTelefone());
        telefone.setHorizontalAlignment(SwingConstants.CENTER);
        telefone.setMaximumSize(new Dimension(600, 30));
        add(telefone);
        
        JLabel dataNascimento = new JLabel("Data de Nascimento: " + aluno.getDataNascimento());
        dataNascimento.setHorizontalAlignment(SwingConstants.CENTER);
        dataNascimento.setMaximumSize(new Dimension(600, 30));
        add(dataNascimento);
        
        
        
        
    }
    
    
}
