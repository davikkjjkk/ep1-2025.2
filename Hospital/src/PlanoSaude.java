import java.util.HashMap;
import java.util.Map;

public class PlanoSaude {
    private String nome;
    private Map<String, Double> descontosPorEspecialidade;
    private boolean planoEspecial;
    private double descontoIdoso;

    public PlanoSaude(String nome, boolean planoEspecial, double descontoIdoso){
        this.nome = nome;
        this.planoEspecial = planoEspecial;
        this.descontoIdoso = descontoIdoso;
        this.descontosPorEspecialidade = new HashMap<>();
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome ){
        this.nome = nome;
    }

    public boolean getPlanoEspecial(){
        return planoEspecial;
    }

    public void setPlanoEspecial(boolean planoEspecial){
        this.planoEspecial = planoEspecial;
    }

    public double getDescontoIdoso(){
        return descontoIdoso;
    }

    public void setDescontoIdoso(double descontoIdoso){
        this.descontoIdoso = descontoIdoso;
    }

    public void adicionarDesconto(String especialidade, double desconto){
        descontosPorEspecialidade.put(especialidade, desconto);
    }

    public double getDescontoPorEspecialidade(String especialidade){
        return descontosPorEspecialidade.getOrDefault(especialidade, 0.0);
    }
}
