package view;

import beans.Aluno;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Spring;
import javax.swing.SpringLayout;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;
import model.AlunoTableModel;

public class Formulario extends JDialog {
    
    private final JFrame frame;
    private final String titulo;
    private Aluno aluno;
    private boolean isEdit;
    private final AlunoTableModel modelAlunos;
    
    private JLabel Labelmatricula;
    private JLabel labelNome;
    private JLabel labelIdade;
    private JLabel labelCpf;
    private JLabel label;
    private JLabel labelTelefone;
    private JLabel labelData;
    
    private JTextField textMatricula;
    private JTextField textNome;
    private JTextField textCpf;
    private JTextField textTelefone;
    
    private JSpinner spinnerIdade;
    private JSpinner spinnerData;
    
    private MaskFormatter maskCpf;
    private MaskFormatter maskTelefone;
    private NumberFormatter maskMatricula;

    
    public Formulario(JFrame frame, String titulo, AlunoTableModel modelAlunos) {
        super(frame, titulo, DEFAULT_MODALITY_TYPE);
        this.frame = frame;
        this.titulo = titulo;
        this.modelAlunos = modelAlunos;
        this.aluno = new Aluno();
        initComponentes();
    }
    
    public Formulario(JFrame frame, String titulo, AlunoTableModel modelAlunos,
        Aluno aluno) {
        
        this(frame, titulo, modelAlunos);
        this.aluno = aluno;
        this.isEdit = true;
        preencheCampos();
    }
    
    private void initComponentes() {
        
        this.setSize(new Dimension(600, 500));
        this.setPreferredSize(new Dimension(600, 500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        try {
            maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.setPlaceholder("000.000.000-00");
            maskTelefone = new MaskFormatter("(##) ####-####");
            maskTelefone.setPlaceholder("(00) 0000-0000");
            
            maskMatricula = new NumberFormatter();
            maskMatricula.setValueClass(Integer.class);
            maskMatricula.setMinimum(0);
            maskMatricula.setMaximum(1000);
            maskMatricula.setAllowsInvalid(false);
            maskMatricula.setCommitsOnValidEdit(true);
            
        } catch (ParseException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        JLabel labelTitulo = new JLabel(this.titulo, JLabel.CENTER);
        labelTitulo.setFont(new Font(Font.SERIF, Font.BOLD, 16));
        
        Labelmatricula = new JLabel("Matricula:", JLabel.RIGHT);
        textMatricula = new JFormattedTextField(maskMatricula);

        labelNome = new JLabel("Nome:", JLabel.RIGHT);
        textNome = new JTextField();
        
        labelIdade = new JLabel("Idade:", JLabel.RIGHT);
        SpinnerModel spinnerModel = new SpinnerNumberModel(10, 0, 100, 1);
        spinnerIdade = new JSpinner(spinnerModel);
        
        labelCpf = new JLabel("Cpf:", JLabel.RIGHT);
        textCpf = new JFormattedTextField(maskCpf);
        
        labelTelefone = new JLabel("Telefefone:", JLabel.RIGHT);
        textTelefone = new JFormattedTextField(maskTelefone);
        
        labelData = new JLabel("Data de Nascimento:", JLabel.RIGHT);
        SpinnerModel spinnerModelData = new SpinnerDateModel();
        spinnerData = new JSpinner(spinnerModelData);
        spinnerData.setEditor(new JSpinner.DateEditor(spinnerData, "dd/MM/yyyy"));
        
        JButton btnCadastrar = new JButton("SALVAR");
        btnCadastrar.addActionListener((ActionEvent e) -> {
            this.salvaAluno();
        });
        
        JButton btnCancelar = new JButton("CANCELAR");
        btnCancelar.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
        
        Container contentPane = this.getContentPane();
        SpringLayout layout = new SpringLayout();
        contentPane.setLayout(layout);
        
        contentPane.add(labelTitulo);
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, labelTitulo, 0, SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.NORTH, labelTitulo, 15, SpringLayout.NORTH, contentPane);
        
        addCampos(Labelmatricula, textMatricula, layout, contentPane, 20);
        addCampos(labelNome, textNome, layout, contentPane, 15);
        addCampos(labelIdade, spinnerIdade, layout, contentPane, 15);
        addCampos(labelCpf, textCpf, layout, contentPane, 15);
        addCampos(labelTelefone, textTelefone, layout, contentPane, 15);
        addCampos(labelData, spinnerData, layout, contentPane, 15);
        
        contentPane.add(btnCadastrar);
        contentPane.add(btnCancelar);
        
        Spring px = Spring.sum(Spring.width(btnCadastrar), Spring.width(btnCancelar));
        px = Spring.scale(px, 0.5f);
        
        layout.putConstraint(SpringLayout.WEST, btnCadastrar, Spring.minus(px), 
                SpringLayout.HORIZONTAL_CENTER, contentPane);
        layout.putConstraint(SpringLayout.WEST, btnCancelar, 30, 
                SpringLayout.EAST, btnCadastrar);
        
        layout.putConstraint(SpringLayout.SOUTH, btnCadastrar, -70, SpringLayout.SOUTH, contentPane);
        layout.putConstraint(SpringLayout.SOUTH, btnCancelar, -70, SpringLayout.SOUTH, contentPane);

    }
    
    private void addCampos(JLabel label, Component input, SpringLayout layout, 
            Container pane, int mt) {
        
        Component parent = pane.getComponent(pane.getComponentCount() - 1);
        label.setLabelFor(input);
        label.setPreferredSize(new Dimension(150, 30));
        input.setPreferredSize(new Dimension(0, 30));
        pane.add(input);
        pane.add(label);
        
        if(input instanceof JSpinner) {
            layout.putConstraint(SpringLayout.BASELINE, label, 17, SpringLayout.BASELINE, input);
        } else {
            layout.putConstraint(SpringLayout.BASELINE, label, 0, SpringLayout.BASELINE, input);
        }
        
        layout.putConstraint(SpringLayout.NORTH, input, mt, SpringLayout.SOUTH, parent);
        layout.putConstraint(SpringLayout.WEST, label, 20, SpringLayout.WEST, pane);
        layout.putConstraint(SpringLayout.WEST, input, 5, SpringLayout.EAST, label);
        layout.putConstraint(SpringLayout.EAST, input, -20, SpringLayout.EAST, pane);
    }
    
    private void preencheCampos() {
        textMatricula.setText(String.valueOf(aluno.getMatricula()));
        spinnerIdade.setValue(aluno.getIdade());
        textNome.setText(aluno.getNome());
        textCpf.setText(aluno.getCpf());
        textTelefone.setText(aluno.getTelefone());
        

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String source = aluno.getDataNascimento();
        
        try {
            if(!source.isBlank()) {
                Date data = formatter.parse(source);
                spinnerData.setValue(data);
            }
        } catch (ParseException ex) {
            Logger.getLogger(Formulario.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }
     
    
    private void salvaAluno() {
        try {
            int matricula = Integer.parseInt(textMatricula.getText());
            String nome = textNome.getText();
            String cpf = textCpf.getText();
            String telefone = textTelefone.getText();
            Date date = (Date)spinnerData.getValue();
            LocalDate localDete = LocalDate.ofInstant(date.toInstant(), 
                    ZoneId.systemDefault());
        
        aluno.setMatricula(matricula);
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setTelefone(telefone);
        aluno.setIdade((int)spinnerIdade.getValue());
        aluno.setDataNascimento(localDete);
        
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this, 
                    "Um ou mais campos estão incorretos!",
                    "ERRO",
                    JOptionPane.ERROR_MESSAGE
            );
            
            return;
        }
        
        
        if(this.isEdit) {
            modelAlunos.atualizaAluno(aluno);
        } 
        else {
            if (modelAlunos.findAluno(aluno) == -1) {
                modelAlunos.adicionaAluno(aluno);
                
            } else {
                JOptionPane.showMessageDialog(this, 
                        "Já existe um aluno(a) com essa matrícula!", "Erro",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        this.dispose();
    }
}
