public class Main {
    public static void main(String[] args){
        Paciente p1 = new Paciente("Carlos", "12345678900", 30);
        PacienteEspecial p2 = new PacienteEspecial("Ana", "98765432100", 45, "Unimed");

        System.out.println("=== Paciente Comum ===");
        p1.exibirInfo();
        p1.exibirHistorico();

        System.out.println("\n=== Paciente Especial ===");
        p2.exibirInfo();
        p2.exibirHistorico();
    }
}
