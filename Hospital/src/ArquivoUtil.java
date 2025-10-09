import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class ArquivoUtil {

    public static void salvarPacientes(List<Paciente> pacientes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("pacientes.txt"))) {
            for (Paciente p : pacientes) {
                String plano = (p.getPlanoSaude() != null) ? p.getPlanoSaude().getNome() : "SemPlano";
                writer.println(p.getNome() + ";" + p.getCpf() + ";" + p.getIdade() + ";" + plano);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar pacientes: " + e.getMessage());
        }
    }

    public static List<Paciente> lerPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("pacientes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 4) {
                    String nome = dados[0];
                    String cpf = dados[1];
                    int idade = Integer.parseInt(dados[2]);
                    String planoNome = dados[3];
                    
                    if(!planoNome.equals("SemPlano")){
                        PlanoSaude plano = new PlanoSaude(planoNome, true, 0.1);
                        PacienteEspecial p = new PacienteEspecial(nome, cpf, idade, plano);
                        pacientes.add(p);
                    }

                    else{
                        pacientes.add(new Paciente(nome, cpf, idade));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de pacientes não encontrado, criando um novo...");
        } catch (IOException e) {
            System.out.println("Erro ao ler pacientes: " + e.getMessage());
        }

        return pacientes;
    }

    public static void salvarMedicos(List<Medico> medicos) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("medicos.txt"))) {
            for (Medico m : medicos) {
                writer.println(m.getNome() + ";" + m.getCrm() + ";" + m.getEspecialidade());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar médicos: " + e.getMessage());
        }
    }

    public static List<Medico> lerMedicos() {
        List<Medico> medicos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("medicos.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 3) {
                    String nome = dados[0];
                    String crm = dados[1];
                    String especialidade = dados[2];
                    medicos.add(new Medico(nome, crm, especialidade, 0));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de médicos não encontrado, criando um novo...");
        } catch (IOException e) {
            System.out.println("Erro ao ler médicos: " + e.getMessage());
        }

        return medicos;
    }

    public static void salvarConsultas(List<Consulta> consultas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("consultas.txt"))) {
            for (Consulta c : consultas) {
                writer.println(c.getPaciente().getCpf() + ";" +
                               c.getMedico().getCrm() + ";" +
                               c.getDataHora().toString() + ";" +
                               c.getLocal() + ";" +
                               c.getValor());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar consultas: " + e.getMessage());
        }
    }

    public static ArrayList<Consulta> lerConsultas(List<Paciente> pacientes, List<Medico> medicos) {
        ArrayList<Consulta> consultas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("consultas.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5) {
                    String cpf = dados[0];
                    String crm = dados[1];
                    LocalDateTime data = LocalDateTime.parse(dados[2]);
                    String local = dados[3];
                    double valor = Double.parseDouble(dados[4]);

                    Paciente paciente = pacientes.stream().filter(p -> p.getCpf().equals(cpf)).findFirst().orElse(null);
                    Medico medico = medicos.stream().filter(m -> m.getCrm().equals(crm)).findFirst().orElse(null);

                    if (paciente != null && medico != null) {
                        consultas.add(new Consulta(paciente, medico, data, local, valor));
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de consultas não encontrado (novo será criado).");
        } catch (IOException e) {
            System.out.println("Erro ao ler consultas: " + e.getMessage());
        }
        return consultas;
    }

    public static void salvarInternacoes(List<Internacao> internacoes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("internacoes.txt"))) {
            for (Internacao i : internacoes) {
                String dataSaida = (i.getDataSaida() != null) ? i.getDataSaida().toString() : "null";
                writer.println(i.getPaciente().getCpf() + ";" +
                               i.getMedicoResponsavel().getCrm() + ";" +
                               i.getQuarto() + ";" +
                               i.getCusto() + ";" +
                               dataSaida);
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar internações: " + e.getMessage());
        }
    }

    public static ArrayList<Internacao> lerInternacoes(List<Paciente> pacientes, List<Medico> medicos) {
        ArrayList<Internacao> internacoes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("internacoes.txt"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 5) {
                    String cpf = dados[0];
                    String crm = dados[1];
                    int quarto = Integer.parseInt(dados[2]);
                    double custo = Double.parseDouble(dados[3]);
                    String dataSaidaStr = dados[4];

                    Paciente paciente = pacientes.stream().filter(p -> p.getCpf().equals(cpf)).findFirst().orElse(null);
                    Medico medico = medicos.stream().filter(m -> m.getCrm().equals(crm)).findFirst().orElse(null);

                    if (paciente != null && medico != null) {
                        Internacao i = new Internacao(paciente, medico, quarto, custo);
                        if (!dataSaidaStr.equals("null")) {
                            i.setDataSaida(LocalDateTime.parse(dataSaidaStr));
                        }
                        internacoes.add(i);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de internações não encontrado (novo será criado).");
        } catch (IOException e) {
            System.out.println("Erro ao ler internações: " + e.getMessage());
        }
        return internacoes;
    }


}
