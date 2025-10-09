
import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Main{
    private static List<Paciente> pacientes = ArquivoUtil.lerPacientes();
    private static List<Medico> medicos = ArquivoUtil.lerMedicos();
    private static List<Consulta> consultas = ArquivoUtil.lerConsultas(pacientes, medicos);
    private static List<Internacao> internacoes = ArquivoUtil.lerInternacoes(pacientes, medicos);

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
            System.out.println("5 - Exibir Relatorios");
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
                    exibirRelatorios(sc);
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

        PlanoSaude plano = null;
        if(especial){
            System.out.println("Vamos cadastrar o plano de saude do paciente especial");

            System.out.print("Nome do plano: ");
            String nomePlano = sc.nextLine();

            System.out.print("Informe o desconto para Idosos (em %): ");
            double descontoIdoso = sc.nextDouble() /100;
            sc.nextLine();

            plano = new PlanoSaude(nomePlano, especial, descontoIdoso);

            System.out.print("Deseja adicionar descontos por especialidade? (Sim/Nao): ");
            if(sc.nextLine().equalsIgnoreCase("Sim")){
                boolean adicionando = true;
                while (adicionando){
                    System.out.print("Especialidade: ");
                    String esp = sc.nextLine();

                    System.out.print("Desconto para essa especialidade (%): ");
                    double desc = sc.nextDouble() /100;
                    sc.nextLine();

                    plano.adicionarDesconto(esp, desc);

                    System.out.print("Adicionar outra especialidade (Sim/Nao): ");
                    adicionando = sc.nextLine().equalsIgnoreCase("Sim");
                }
            }

            System.out.println("Plano de Saude cadastrado com sucesso.");
        }

        Paciente p = new Paciente(nome, cpf, idade);
        p.setEspecial(especial);

        if(plano != null && p instanceof PacienteEspecial){
            ((PacienteEspecial) p).setPlanoSaude(plano);
        }

        pacientes.add(p);
        ArquivoUtil.salvarPacientes(pacientes);
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
        ArquivoUtil.salvarMedicos(medicos);
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
        ArquivoUtil.salvarConsultas(consultas);

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
        ArquivoUtil.salvarInternacoes(internacoes);
        
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
        ArquivoUtil.salvarInternacoes(internacoes);
        System.out.println("Paciente liberado.");
    }

    private static void exibirRelatorios(Scanner sc){
      Relatorios rel = new Relatorios(pacientes, medicos, consultas, internacoes);
      
      System.out.println("Escolha");
      System.out.println("1 - Listar Pacientes");
      System.out.println("2 - Listar Medicos");
      System.out.println("3 - Consultas Futuras");
      System.out.println("4 - Consultas Passadas");
      System.out.println("5 - Pacientes Internados");
      System.out.println("6 - Medico Que Mais Atendeu");
      System.out.println("7 - Especialidade Mais Procurada");
      System.out.println("8 - Economia por Plano de Saude");
      System.out.println("9 - Voltar ao Menu Principal");

      int opc = sc.nextInt();
    sc.nextLine();

    switch (opc) {
        case 1:
            System.out.println("\n--- PACIENTES ---");
            for (Paciente p : rel.getPacientesCadastrados()) {
                System.out.println("- " + p.getNome() + " (" + (p.isEspecial() ? "Especial" : "Comum") + ")");
            }
            break;

        case 2:
            System.out.println("\n--- MÉDICOS ---");
            for (Medico m : rel.getMedicosCadastrados()) {
                System.out.println("- " + m.getNome() + " / " + m.getEspecialidade());
            }
            break;

        case 3:
            System.out.println("\n--- CONSULTAS FUTURAS ---");
            for (Consulta c : rel.getConsultasFuturas()) {
                System.out.println(c.getPaciente().getNome() + " com " + c.getMedico().getNome());
            }
            break;

        case 4:
            System.out.println("\n--- CONSULTAS PASSADAS ---");
            for (Consulta c : rel.getConsultasPassadas()) {
                System.out.println(c.getPaciente().getNome() + " com " + c.getMedico().getNome());
            }
            break;

        case 5:
            System.out.println("\n--- PACIENTES INTERNADOS ---");
            for (Internacao i : rel.getPacientesInternados()) {
                System.out.println(i.getPaciente().getNome() + " no quarto " + i.getQuarto());
            }
            break;

        case 6:
            Medico maisAtendeu = rel.getMedicoQueMaisAtendeu();
            if (maisAtendeu != null) {
                System.out.println("Médico que mais atendeu: " + maisAtendeu.getNome());
            } else {
                System.out.println("Nenhum médico encontrado.");
            }
            break;

        case 7:
            String esp = rel.getEspecialidadeMaisProcurada();
            System.out.println("Especialidade mais procurada: " + (esp != null ? esp : "Nenhuma registrada."));
            break;

        case 8:
            System.out.print("Digite o nome do plano: ");
            String nomePlano = sc.nextLine();
            double economia = rel.getEconomiaPorPlano(nomePlano);
            int qtdPacientes = rel.getQuantidadePacientesPorPlano(nomePlano);

            System.out.println("Plano: " + nomePlano);
            System.out.println("Pacientes cadastrados nesse plano: " + qtdPacientes);
            System.out.println("Economia total gerada: R$ " + String.format("%.2f", economia));
            break;

        default:
            System.out.println("Voltando ao menu principal...");
            break;
    }
  }

}
    
