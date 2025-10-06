
import java.time.LocalDateTime;

public class Internacao{
    private Paciente paciente;
    private Medico medicoResponsavel;
    private LocalDateTime dataEntrada;
    private LocalDateTime dataSaida;
    private int quarto;
    private double custo;
    private String status;

    public Internacao(Paciente paciente, Medico medicoResponsavel, int quarto, double custo){
        this.paciente = paciente;
        this.medicoResponsavel = medicoResponsavel;
        this.quarto = quarto;
        this.custo = custo;
        this.status = "Ativa";
        this.dataEntrada = LocalDateTime.now();
        this.dataSaida = null;
    }

    public void darAlta(LocalDateTime dataSaida){
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

    public LocalDateTime getDataEntrada(){
        return dataEntrada;
    }

    public LocalDateTime getDataSaida(){
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida){
        this.dataSaida = dataSaida;
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
