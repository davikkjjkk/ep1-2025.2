import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main{
    private static ArrayList<Paciente> pacientes = new ArrayList<>();
    private static ArrayList<Medico> medicos = new ArrayList<>();
    private static ArrayList<Consulta> consultas = new ArrayList<>();
    private static ArrayList<Internacao> internacoes = new ArrayList<>();

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        while(opcao!=0){
            System.out.println("\n=== HOSPITAL CRUZ VERMELHA ===");
            System.out.println("Escolha uma opcao: ");
            System.out.println("1 - Cadastrar Pacientes");
            System.out.println("2 - Cadastrar Medicos");
            System.out.println("3 - Agendar Consulta");
            System.out.println("4 - Internacoes");
            System.out.println("5 - Relatorios");
            System.out.println("0 - Sair");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao){
                case 1:
                    cadastrarPaciente(sc);
                    break;
                case 2:
                    cadastrarMedico(sc);
                    break;
                case 3:
                    agendarConsulta(sc);
                    break;
                case 4:
                    gerenciarInternacoes(sc);
                    break;
                case 5:
                    gerarRelatorios(sc);
                    break;
                case 0:
                    System.out.println("Encerrando o sistema...");
                default:
                    System.out.println("Opcao invalida."); 
            }
        }

        sc.close();
    }

    private static void cadastrarPaciente(Scanner sc){
        System.out.println("Nome: ");
        String nome = sc.nextLine();
        System.out.println("CPF: ");
        String cpf = sc.nextLine();
        System.out.println("Idade: ");
        int idade =  sc.nextInt();
        sc.nextLine();
        System.out.println("Eh paciente especial? (Sim/Nao)");
        boolean especial = sc.nextLine().equalsIgnoreCase("Sim");

        Paciente p = new Paciente(nome, cpf, idade);
        p.setEspecial(especial);
        pacientes.add(p);
        System.out.println("Paciente cadastrado com sucesso.");
    }

    private static void cadastrarMedico(Scanner sc){
        System.out.println("Nome: ");
        String nomeMedico = sc.nextLine();

        System.out.println("Escolha a especialidade: ");
        System.out.println("1 - Cardiologista");
        System.out.println("2 - Pediatria");
        System.out.println("3 - Ortopedista");
        System.out.println("4 - Dermatologista");
        System.out.println("5 - Neurologista");
        System.out.println("6 - Psiquiatra");

        int esp = sc.nextInt();
        sc.nextLine();

        System.out.print("CRM: ");
        String crm = sc.nextLine();
        System.out.print("Custo da consulta: ");
        double custo = sc.nextDouble();
        sc.nextLine();

        Medico medicoNovo;
        switch(esp){
            case 1:
            medicoNovo = new Cardiologista(nomeMedico, crm, custo);
            break;
            case 2:
            medicoNovo = new Pediatra(nomeMedico, crm, custo);
            break;
            case 3:
            medicoNovo = new Ortopedista(nomeMedico, crm, custo);
            break;
            case 4:
            medicoNovo = new Dermatologista(nomeMedico, crm, custo);
            break;
            case 5:
            medicoNovo = new Neurologista(nomeMedico, crm, custo);
            break;
            case 6:
            medicoNovo = new Psiquiatra(nomeMedico, crm, custo);
            break;
            default:
            medicoNovo = new Medico(nomeMedico, crm, "Clinico Geral", custo);
            break;
        }

        medicos.add(medicoNovo);
        System.out.println("Medico cadastrado.");
    }

    private static void agendarConsulta(Scanner sc){
        if(pacientes.isEmpty() || medicos.isEmpty()){
            System.out.println("Cadastre pelo menos um medico e um paciente primeiro.");
            return;
        }
        System.out.println("Escolha o paciente: ");
        for(int i = 0; i<pacientes.size(); i++){
            System.out.println(i + " - " + pacientes.get(i).getNome());
        }

        int idxPaciente = sc.nextInt();
        sc.nextLine();

        System.out.println("Escolha o medico: ");
        for(int i=0; i<medicos.size(); i++){
            System.out.println(i + " - " + medicos.get(i).getNome() + " (" + medicos.get(i).getEspecialidade() + ")");
        }

        int idxMedico = sc.nextInt();
        sc.nextLine();

        System.out.print("Local da Consulta: ");
        String local = sc.nextLine();
        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        sc.nextLine();

        LocalDateTime agora = LocalDateTime.now();
        Consulta c = new Consulta(pacientes.get(idxPaciente), medicos.get(idxMedico), agora, local, valor);
        consultas.add(c);

        pacientes.get(idxPaciente).adicionarConsulta(c);
        System.out.println("Consulta agendada.");
    }

    private static void gerenciarInternacoes(Scanner sc){
        System.out.println("Escolha: ");
        System.out.println("\n1 - Registrar Internação");
        System.out.println("2 - Liberar Paciente");

        int opc = sc.nextInt();
        sc.nextLine();

        switch(opc){
            case 1:
            registrarInternacao(sc);
            break;
            case 2:
            liberarInternacao(sc);
            break;
            default:
            System.out.println("Opcao invalida.");
        }
        
    }

    private static void registrarInternacao(Scanner sc){
        if(pacientes.isEmpty() || medicos.isEmpty()){
            System.out.println("Cadastre pelo menos um medico e um paciente antes");
            return;
        }

        System.out.println("Escolha o paciente: ");
        for(int i = 0; i<pacientes.size(); i++){
            System.out.println(i + " - " + pacientes.get(i).getNome());
        }

        int idxPaciente = sc.nextInt();
        sc.nextLine();

        System.out.println("Escolha o medico responsavel: ");
        for(int i = 0; i<medicos.size(); i++){
            System.out.println(i + " - " + medicos.get(i).getNome());
        }

        int idxMedico = sc.nextInt();
        sc.nextLine();

        System.out.print("Numero do quarto: ");
        int quarto = sc.nextInt();
        sc.nextLine();
        System.out.print("Custo da Internacao: ");
        double custo = sc.nextDouble();
        sc.nextLine();

        Internacao i = new Internacao(pacientes.get(idxPaciente), medicos.get(idxMedico), quarto, custo);
        internacoes.add(i);
        
        pacientes.get(idxPaciente).adicionarInternacao("Internacao no  Quarto: " + quarto);
        System.out.println("Internacao registrada");
    }

    private static void liberarInternacao(Scanner sc){
        if(internacoes.isEmpty()){
            System.out.println("Nenhuma internacao registrada.");
            return;
        }

        System.out.println("Escolha qual internacao deseja liberar:");
        for(int i = 0; i < internacoes.size(); i++){
            System.out.println(i + " - " + internacoes.get(i).getPaciente().getNome());
        }

        int idx = sc.nextInt();
        sc.nextLine();

        internacoes.get(idx).setDataSaida(LocalDateTime.now());
        System.out.println("Paciente liberado.");
    }

    private static void gerarRelatorios(Scanner sc){
        System.out.println("Escolha: ");
        System.out.println("\n1 - Listar Pacientes");
        System.out.println("2 - Listar Medicos");
        System.out.println("3 - Consultas Agendadas");
        System.out.println("4 - Pacientes Internados");

        int opc = sc.nextInt();
        sc.nextLine();

        switch (opc) {
            case 1:
                for(Paciente p : pacientes){
                    System.out.println("- " + p.getNome() + " (" + (p.isEspecial() ? "Especial" : "Comum") + ")");
                }
                break;
        
            case 2:
            for(Medico m : medicos){
                System.out.println("- " + m.getNome() + " /" + m.getEspecialidade());
            }
                break;

            case 3:
            for(Consulta c : consultas){
                System.out.println("- " + c.getPaciente().getNome() + " com" + c.getMedico().getNome());
            }
            break;

            case 4:
            for(Internacao i : internacoes){
                if(i.getDataSaida() == null){
                    System.out.println("- " + i.getPaciente().getNome() + " no quarto" + i.getQuarto());

                }
            }
            break;

            default:
            System.out.println("Opcao invalida");
        }
    }
}