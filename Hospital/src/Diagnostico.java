import java.util.ArrayList;
import java.util.List;


public class Diagnostico {
    private String descricao;
    private List<String> medicamentos;
    
    public Diagnostico(String descricao){
        this.descricao = descricao;
        this.medicamentos = new ArrayList<>();
    }

    public String getDescricao(){
        return descricao;
    }

    public List<String> getMedicamentos(){
        return medicamentos;
    }

    public void adicionarMedicamento(String medicamento){
        medicamentos.add(medicamento);
    }
}
