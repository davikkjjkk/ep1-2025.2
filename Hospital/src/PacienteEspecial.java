public class PacienteEspecial extends Paciente{
    private PlanoSaude planoSaude;

    public PacienteEspecial(String nome, String cpf, int idade, PlanoSaude planoSaude){
        super(nome, cpf, idade);
        this.planoSaude = planoSaude;
        this.setEspecial(true);
    }

    public PlanoSaude getPlanoSaude(){
        return planoSaude;
    }

    public void setPlanoSaude(PlanoSaude planoSaude){
        this.planoSaude = planoSaude;
    }

}
