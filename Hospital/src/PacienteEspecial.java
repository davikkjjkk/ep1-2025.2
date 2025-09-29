public class PacienteEspecial extends Paciente{
    private PlanoSaude planoSaude;

    public PacienteEspecial(String nome, String cpf, int idade, PlanoSaude planoSaude){
        super(nome, cpf, idade);
        this.planoSaude = planoSaude;
    }

    public PlanoSaude getPlanoSaude(){
        return planoSaude;
    }

    public void setPlanoSaude(PlanoSaude planoSaude){
        this.planoSaude = planoSaude;
    }

    public void exibirInfo(){
        super.exibirInfo();
        System.out.println("Plano de Saude: " + planoSaude);
    }
}
