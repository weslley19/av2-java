package model;

import beans.Aluno;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class AlunoTableModel extends AbstractTableModel{
    
    private final String colunas[] = {
        "MATRICULA", "NOME", "IDADE", "CPF", "DATA", "AÇÕES"
    };
    private final List<Aluno> alunos;
    
    public static final int COLUNA_MATRICULA = 0;
    public static final int COLUNA_NOME = 1;
    public static final int COLUNA_IDADE = 2;
    public static final int COLUNA_CPF = 3;
    public static final int COLUNA_DATA = 4;
    public static final int COLUNA_ACOES = 5;

    public AlunoTableModel() {
        alunos = new ArrayList<>();
    }
    
    public AlunoTableModel(List<Aluno> alunos) {
        this.alunos = alunos;
    }
    
    public void removeAluno(int row) {
        alunos.remove(row);
        fireTableRowsDeleted(row, row);
    }
    
    public void adicionaAluno(Aluno aluno) {
        alunos.add(aluno);
        fireTableDataChanged();
    }
    
    public void atualizaAluno(Aluno aluno, int row) {
        alunos.set(row, aluno);
        fireTableRowsUpdated(row, row);
    }
    
    public void atualizaAluno(Aluno aluno) {
        int row = alunos.indexOf(aluno);
        alunos.set(row, aluno);
        fireTableRowsUpdated(row, row);
    }
    
    public Aluno getAluno(int row) {
        return alunos.get(row);
    }
    
    public int findAluno(Aluno aluno) {
        return alunos.indexOf(aluno);
    }
    
    public List<Aluno> getAlunos() {
        return this.alunos;
    }

    @Override
    public int getRowCount() {
        return this.alunos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case COLUNA_NOME:
                return this.alunos.get(rowIndex).getNome();
            case COLUNA_MATRICULA:
                return this.alunos.get(rowIndex).getMatricula();
            case COLUNA_CPF:
                return this.alunos.get(rowIndex).getCpf();
            case COLUNA_IDADE:
                return this.alunos.get(rowIndex).getIdade();
            case COLUNA_DATA:
                return this.alunos.get(rowIndex).getDataNascimento();
        }
        
        return null;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch(columnIndex) {
            case COLUNA_NOME:
                this.alunos.get(rowIndex).setNome((String)aValue);
            case COLUNA_MATRICULA:
                this.alunos.get(rowIndex).setMatricula((int)aValue);
            case COLUNA_CPF:
                this.alunos.get(rowIndex).setCpf((String)aValue);
            case COLUNA_IDADE:
                this.alunos.get(rowIndex).setIdade((int)aValue);
            case COLUNA_DATA:
                this.alunos.get(rowIndex).setDataNascimento((String)aValue);
        }
        
        this.fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == COLUNA_ACOES;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case COLUNA_MATRICULA:
                return Integer.class;
            default:
                return String.class;
        }
    }
}