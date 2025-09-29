public class PlanoSaude {
    private String nome;
    private double desconto;

    public PlanoSaude(String nome, double desconto){
        this.nome = nome;
        this.desconto = desconto;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome ){
        this.nome = nome;
    }

    public double getDesconto(){
        return desconto;
    }

    public void setDesconto(double desconto){
        this.desconto = desconto;
    }

    public String toString(){
        return nome + " (Desconto: )" + (desconto*100) + "%";
    }
}
