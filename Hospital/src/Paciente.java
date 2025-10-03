import java.util.ArrayList;
import java.util.List;

public class Paciente {
    private String nome;
    private final String cpf;
    private int idade;
    private boolean especial;
    private ArrayList<String> historicoInternacoes;
    private ArrayList<Consulta> historicoConsultas;

    public Paciente (String nome, String cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
        this.especial = false;
        this.historicoInternacoes = new ArrayList<>();
        this.historicoConsultas = new ArrayList<>();

    }

    public boolean isEspecial(){
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
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


    public void adicionarConsulta(Consulta consulta){
        historicoConsultas.add(consulta);
    }

    public List<Consulta> getHistoricoConsultas(){
        return historicoConsultas;
    }

    public void adicionarInternacao(String internacao){
        historicoInternacoes.add(internacao);
    }

    public List<String> getHistoricoInternacoes(){
        return historicoInternacoes;
    }

}
