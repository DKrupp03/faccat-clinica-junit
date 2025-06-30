package service;

import model.Consulta;
import model.Paciente;

import java.time.LocalDateTime;
import java.util.*;

public class ConsultaService {
    private final Map<String, Paciente> pacientes = new HashMap<>();

    public void agendarConsulta(String cpf, String nome, String dataHoraTexto) {
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraTexto);
        Consulta consulta = new Consulta(nome, dataHora);

        Paciente paciente = pacientes.get(cpf);
        if (paciente == null) {
            paciente = new Paciente(nome, cpf);
            pacientes.put(cpf, paciente);
        }

        paciente.adicionarConsulta(consulta);
    }

    public List<Consulta> getConsultas(String cpf) {
        Paciente paciente = pacientes.get(cpf);
        if (paciente != null) {
            return paciente.getConsultas();
        }
        return new ArrayList<>();
    }

    public Paciente getPaciente(String cpf) {
        return pacientes.get(cpf);
    }
}
