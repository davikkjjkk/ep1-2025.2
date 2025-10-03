import java.util.ArrayList;

public class Medico {
    private String nome;
    private String crm;
    private String especialidade;
    private double custoConsulta;
    private ArrayList<String> agendaHorarios;

    public Medico(String nome, String crm, String especialidade, double custoConsulta){
        this.nome = nome;
        this.crm = crm;
        this.especialidade = especialidade;
        this.custoConsulta = custoConsulta;
        this.agendaHorarios = new ArrayList<>();
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getCrm(){
        return crm;
    }

    public void setCrm(String crm){
        this.crm = crm;
    }

    public String getEspecialidade(){
        return especialidade;
    }

    public void setEspecialidade(String especialidade){
        this.especialidade = especialidade;
    }

    public double getCustoConsulta(){
        return custoConsulta;
    }

    public void setCustoConsulta(double custoConsulta){
        this.custoConsulta = custoConsulta;
    }

    public ArrayList<String> getAgendaHorarios(){
        return agendaHorarios;
    }

    public void adicionarHorario(String horario){
        agendaHorarios.add(horario);
    }

    public void removerHorario(String horario){
        agendaHorarios.remove(horario);
    }
}
