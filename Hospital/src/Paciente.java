import java.util.ArrayList;

public class Paciente {
    private String nome;
    private final String cpf;
    private int idade;
    private ArrayList<String> historicoInternacoes;
    private ArrayList<String> historicoConsultas;

    public Paciente (String nome, String cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.historicoInternacoes = new ArrayList<>();
        this.historicoConsultas = new ArrayList<>();

    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCpf(){
        return cpf;
    }

    public int getIdade(){
        return idade;
    }

    public void setIdade(int idade){
        this.idade = idade;
    } 

    public void adicionarConsulta(String consulta){
        historicoConsultas.add(consulta);
    }

    public void adicionarInternacao(String internacao){
        historicoInternacoes.add(internacao);
    }

    public void exibirHistorico(){
        System.out.println("Historico de Consultas: "  + historicoConsultas);
        System.out.println("Historico de internacoes: " + historicoInternacoes);
    }

    public void exibirInfo(){
        System.out.println("Nome: " + nome + " | CPF: " + cpf + " | Idade: " + idade);
    }

}
