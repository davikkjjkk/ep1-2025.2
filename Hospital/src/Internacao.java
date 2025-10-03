import java.time.LocalDate;

public class Internacao{
    private Paciente paciente;
    private Medico medicoResponsavel;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private int quarto;
    private double custo;
    private String status;

    public Internacao(Paciente paciente, Medico medicoResponsavel, LocalDate dataEntrada, int quarto, double custo){
        this.paciente = paciente;
        this.medicoResponsavel = medicoResponsavel;
        this.dataEntrada = dataEntrada;
        this.quarto = quarto;
        this.status = "Ativa";
    }

    public void darAlta(LocalDate dataSaida){
        this.dataSaida = dataSaida;
        this.status = "Concluida";
    }

    public void cancelar(){
        this.status = "Cancelada";
    }

    public Paciente getPaciente(){
        return paciente;
    }

    public Medico getMedicoResponsavel(){
        return medicoResponsavel;
    }

    public LocalDate getDataEntrada(){
        return dataEntrada;
    }

    public LocalDate getDataSaida(){
        return dataSaida;
    }

    public int getQuarto(){
        return quarto;
    }

    public double getCusto(){
        return custo;
    }

    public String getStatus(){
        return status;
    }
}
