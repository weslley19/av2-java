package view;

import view.render.ActionEditorRender;
import beans.Aluno;
import model.AlunoTableModel;

import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;

public class TelaPrincipal extends JFrame{
    
    private List<Aluno> alunos;
    private AlunoTableModel model;
    
    private final Color colorTemaHex;

    public TelaPrincipal(String titulo) {
        super(titulo);
        this.colorTemaHex = Color.decode("#FFFFFF");
        loadData();
        initComponentes();
    }
    
    private void loadData() {
        this.alunos = new ArrayList<>();
        model = new AlunoTableModel(alunos);
    }
    
    private void initComponentes() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setPreferredSize(new Dimension(600, 600));
        this.getContentPane().setBackground(this.colorTemaHex);
        this.setLayout(new BorderLayout(10, 15));
        
        initPanelButton();
        initPanelListaAlunos();
    }
    
    private void initPanelButton(){
        Border blackline = BorderFactory.createLineBorder(Color.BLACK, 1);
        JPanel panelCadastro = new PanelCadastro(this, model);
        panelCadastro.setBackground(Color.decode("#D1F9F9"));
        panelCadastro.setBorder(blackline);
        panelCadastro.setPreferredSize(new Dimension(WIDTH, 90));
        this.add(panelCadastro, BorderLayout.PAGE_START);
        
    }
    
    private void initPanelListaAlunos() {
        TitledBorder titleBorder = BorderFactory.createTitledBorder("ALUNOS CADASTRADOS");
        titleBorder.setBorder(BorderFactory.createEmptyBorder(10, 5, 5, 5));
        titleBorder.setTitleJustification(TitledBorder.CENTER);
        titleBorder.setTitleFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        
        JPanel panelListaAluno = new JPanel();
        panelListaAluno.setLayout(new BorderLayout(5, 0));
        panelListaAluno.setBorder(titleBorder);
        panelListaAluno.setBackground(this.colorTemaHex);
        
        JTable tabela = createTabela();
        JScrollPane scrollPanel = new JScrollPane(tabela);
        scrollPanel.getViewport().setBackground(this.colorTemaHex);
        scrollPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        
        panelListaAluno.add(scrollPanel, BorderLayout.CENTER);
        this.add(panelListaAluno, BorderLayout.CENTER);
    }
    
    private JTable createTabela() {
        var actionRender = new ActionTableAluno();
        JTable tabela = new JTable(this.model);   
        
        DefaultTableCellRenderer render = new DefaultTableCellRenderer();
        render.setHorizontalAlignment(SwingConstants.CENTER);
        
        for(int i = 0; i < tabela.getColumnCount(); i++) {
            tabela.setDefaultRenderer(tabela.getColumnClass(i), render);
        }
        
        var colAcoes = tabela.getColumnModel().getColumn(AlunoTableModel.COLUNA_ACOES);
        colAcoes.setCellRenderer(actionRender.getRenderer());
        colAcoes.setCellEditor(actionRender);
        colAcoes.setMaxWidth(120);
        colAcoes.setMinWidth(120);
        
        
        tabela.getTableHeader().setPreferredSize(new Dimension(0, 30));
        tabela.getTableHeader().setBackground(Color.decode("#87CEEB"));
        tabela.setRowHeight(30);
        tabela.setSelectionBackground(Color.decode("#CDDAFD"));
        tabela.setIntercellSpacing(new Dimension(1, 1));
        tabela.setFocusable(false);
   
        return tabela;
    }
    
    private class ActionTableAluno extends ActionEditorRender {

        @Override
        public void actionVisualizar(JTable table, int row, int col) {
            Aluno aluno = model.getAluno(row);
            Exibicao exibirInfo = new Exibicao(aluno, TelaPrincipal.this);
            exibirInfo.pack();
            exibirInfo.setVisible(true);
            
        }

        @Override
        public void actionEditar(JTable table, int row, int col) {
            Aluno aluno = model.getAluno(row);

            Formulario form = new Formulario(
                    TelaPrincipal.this,
                    "Editar Aluno", model,
                    aluno
            );
            
            form.pack();
            form.setVisible(true);
            
        }

        @Override
        public void actionDeletar(JTable table, int row, int col) {
            
            final JOptionPane optionPane = new JOptionPane( 
                    "Deseja realmente remover esse aluno(a)?",
                    JOptionPane.QUESTION_MESSAGE,
                    JOptionPane.YES_NO_OPTION);
            
            final JDialog dialog = optionPane.createDialog("Excluir");
            dialog.setVisible(true);
            
            if ((int)optionPane.getValue() == JOptionPane.YES_OPTION) {
                model.removeAluno(row);
            }
        }
    }
}
