import java.util.ArrayList;
import java.util.List;

public class Relatorios{
    private List<Paciente> pacientes;
    private List<Medico> medicos;
    private List<Consulta> consultas;
    private List<Internacao> internacoes;

    public Relatorios(List<Paciente> pacientes, List<Medico> medicos, List<Consulta> consultas, List<Internacao> internacoes){
        this.pacientes = pacientes;
        this.medicos = medicos;
        this.consultas = consultas;
        this.internacoes = internacoes;
    }

    public List<Paciente> getPacientesCadastrados(){
        return pacientes;
    }

    public List<Medico> getMedicosCadastrados(){
        return medicos;
    }

    public List<Consulta> getTodasConsultas(){
        return consultas;
    }

    public List<Consulta> getConsultasFuturas(){
        List<Consulta> futuras = new ArrayList<>();
        for(Consulta c : consultas){
            if(c.isFutura()){
                futuras.add(c);
            }
        }

        return futuras;
    }

    public List<Consulta> getConsultasPassadas(){
        List<Consulta> passadas = new ArrayList<>();
        for(Consulta c : consultas){
            if(!c.isFutura()){
                passadas.add(c);
            }
        }
        return passadas;
    }

    public List<Consulta> getConsultasPorPaciente(Paciente paciente){
        List<Consulta> resultado = new ArrayList<>();
        for(Consulta c : consultas){
            if(c.getPaciente().equals(paciente)){
                resultado.add(c);
            }
        }

        return resultado;

    }

    public List<Consulta> getConsultasPorMedico(Medico medico){
        List<Consulta> resultado = new ArrayList<>();
        for(Consulta c : consultas){
            if(c.getMedico().equals(medico)){
                resultado.add(c);
            }
        }

        return resultado;
    }

    public List<Internacao> getPacientesInternados(){
        List<Internacao> internados = new ArrayList<>();
        for(Internacao i : internacoes){
            if(i.getDataSaida() == null){
                internados.add(i);
            }
        }

        return internados;
    }

    public Medico getMedicoQueMaisAtendeu(){
        if(medicos.isEmpty()) return null;

        Medico maisAtendeu = medicos.get(0);
        for(Medico m : medicos){
            if(m.getNumeroConsultas() > maisAtendeu.getNumeroConsultas()){
                maisAtendeu = m;
            }
        }

        return maisAtendeu;
    }

    public String getEspecialidadeMaisProcurada(){
        String maisProcurada = null;
        int maiorContagem = 0;

        for(Medico m : medicos){
            int contagem = 0;
            for(Consulta c : consultas){
                if(c.getMedico().getEspecialidade().equalsIgnoreCase(m.getEspecialidade())){
                    contagem++;
                }
            }

            if(contagem>maiorContagem){
                maiorContagem = contagem;
                maisProcurada = m.getEspecialidade();
            }
        
        }

        return maisProcurada;
    }

    public double getEconomiaPorPlano(String nomePlano){
        double total = 0;
        for(Paciente p : pacientes){
            if(p.getPlanoSaude() != null && p.getPlanoSaude().getNome().equalsIgnoreCase(nomePlano)){
                for(Consulta c : p.getHistoricoConsultas()){
                    double desconto = c.getValor()*p.getPlanoSaude().getDescontoPorEspecialidade(c.getMedico().getEspecialidade());
                        total += desconto;
                    
                }
            }
        }
        return total;
    }

    public int getQuantidadePacientesPorPlano(String nomePlano){
        int contador = 0;
        for(Paciente p : pacientes){
            if (p.getPlanoSaude() != null && p.getPlanoSaude().getNome().equalsIgnoreCase(nomePlano)){
                contador++;
            }
        }
        return contador;
    }

}