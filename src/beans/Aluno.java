package beans;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Aluno {
    private int matricula;
    private String nome;
    private int idade;
    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;

    public Aluno() { }

    public Aluno(int matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
    }
    
    /** Método para retorno do CPF do Aluno
    * @return String - Nr do CPF*/
    public String getCpf() {
        return cpf;
    }
    
    /** Método para retorno da matrícula do Aluno
    * @return int - Nr da Matrícula*/
    public int getMatricula() {
        return matricula;
    }
    
    /** Método para retorno do Telefone do Aluno
    * @return String - Nr do Telefone*/
    public String getTelefone() {
        return telefone;
    }
    
    /** Método para retorno do Nome do Aluno
    * @return String - Nome do Aluno*/
    public String getNome() {
        return nome;
    }
    
    /** Método para retorno do Idade do Aluno
    * @return int - Idade do Aluno*/
    public int getIdade() {
        return idade;
    }
    
    /** Método para retorno da Data de Nascimento do Aluno
    * @return String - Data de Nascimento*/
    public String getDataNascimento() {
        if(dataNascimento == null) return "";
        return dataNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
    
    /** Método para settar o nome do Aluno
     * @param nome - Nome do Aluno*/
    public void setNome(String nome) {
        this.nome = nome;
    }
   
    /** Método para settar o CPF do Aluno
     * @param cpf - Nr do CPF*/
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    /** Método para settar o telefone do Aluno
     * @param telefone - Nr do Telefone*/
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    /** Método para settar a matricula do Aluno
     * @param matricula - Nr da Matricula*/
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }
    
    /** Método para settar a idade do Aluno
     * @param idade - Idade do Aluno*/
    public void setIdade(int idade) {
        this.idade = idade;
    }
    /** Método para settar a Data de Nascimento do Aluno
     * @param dataNascimento - A data de nascimento do Aluno*/
    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    /** Método para settar a Data de Nascimento do Aluno
     * @param data - A data de nascimento do Aluno no fomrato de texto
     */
    public void setDataNascimento(String data) {
        this.dataNascimento = LocalDate.parse(data,
                DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    @Override
    public String toString() {
        return String.format("Nome: %s, matricula: %d", nome, matricula);
    }
    
    //Compara dois objetos: se forem iguais, retorna true, senão, falso
    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        
        if(!(obj instanceof Aluno)) return false;
        
        Aluno aluno = (Aluno)obj;
        
        return (aluno.matricula == this.matricula);
    }
    //Gera o hashCode unico do Objeto Aluno
    @Override
    public int hashCode() { 
        int hash = 5;
        hash = 71 * hash + this.matricula;
        return hash;
    }
    
}

