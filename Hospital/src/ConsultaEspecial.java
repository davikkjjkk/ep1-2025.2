import java.time.LocalDateTime;

public class ConsultaEspecial extends Consulta {
    private double descontoExtra;

    public ConsultaEspecial(Paciente paciente, Medico medico, LocalDateTime dataHora, String local, double valor, double descontoExtra){
        super(paciente, medico, dataHora, local, valor);
        this.descontoExtra = descontoExtra;
        aplicarDesconto();
    }

    private void aplicarDesconto(){
        double valorAtual = getValor();
        double novoValor = valorAtual - (valorAtual * descontoExtra);
        setValor(novoValor);
    }

    public double getDescontoExtra(){
        return descontoExtra;
    }

    public void setDescontoExtra(double descontoExtra){
        this.descontoExtra = descontoExtra;
    }
}
