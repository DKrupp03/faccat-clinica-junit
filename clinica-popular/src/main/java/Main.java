import service.ConsultaService;

public class Main {
    public static void main(String[] args) {
        ConsultaService service = new ConsultaService();

        service.agendarConsulta("12345678900", "João", "2025-06-30T09:00");
        service.agendarConsulta("98765432100", "Maria", "2025-07-01T14:00");

        System.out.println("Consultas de João:");
        service.getConsultas("12345678900").forEach(c ->
                System.out.println("- " + c.getPaciente() + " em " + c.getDataHora())
        );

        System.out.println("Consultas de Maria:");
        service.getConsultas("98765432100").forEach(c ->
                System.out.println("- " + c.getPaciente() + " em " + c.getDataHora())
        );
    }
}
