import java.time.LocalDateTime;

public class Consulta {
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime dataHora;
    private String local;
    private String status;
    private double valor;
    private Diagnostico diagnostico;

    public Consulta(Paciente paciente, Medico medico, LocalDateTime dataHora, String local, double valor){
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.local = local;
        this.status = "Agendada";
        this.valor = aplicarDesconto(valor);    
    }

    private double aplicarDesconto(double valorBase){
        if(paciente.isEspecial() && paciente instanceof PacienteEspecial){
            PacienteEspecial p = (PacienteEspecial) paciente;
            double desconto = p.getPlanoSaude().getDesconto();
            return valorBase - (valorBase * desconto);
        }
        return valorBase;
    }

    public Paciente getPaciente(){
        return paciente;
    }

    public Medico getMedico(){
        return medico;
    }

    public LocalDateTime getDataHora(){
        return dataHora;
    }

    public String getLocal(){
        return local;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public double getValor(){
        return valor;
    }

    public Diagnostico getDiagnostico(){
        return diagnostico;
    }

    public void registrarDiagnostico(Diagnostico diagnostico){
        this.diagnostico = diagnostico;
        this.status = "Concluída";
        paciente.adicionarConsulta(this);
    }


}
