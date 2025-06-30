package test;

import model.Consulta;
import model.Paciente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.ConsultaService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConsultaServiceTest {
    private ConsultaService service;

    @BeforeEach
    void setup() {
        service = new ConsultaService();
    }

    // ✅ TESTES ANTIGOS MANTIDOS
    @Test
    void deveAgendarConsulta() {
        service.agendarConsulta("11111111111", "Ana", "2025-06-25T10:00");
        List<Consulta> consultas = service.getConsultas("11111111111");
        assertEquals(1, consultas.size());
        assertEquals("Ana", consultas.get(0).getPaciente());
    }

    @Test
    void listaDeveComecarVazia() {
        List<Consulta> consultas = service.getConsultas("99999999999");
        assertTrue(consultas.isEmpty());
    }

    // ✅ NOVOS TESTES COM PACIENTE
    @Test
    void deveCriarPacienteComSucesso() {
        service.agendarConsulta("12345678900", "Carlos", "2025-06-30T09:00");
        Paciente paciente = service.getPaciente("12345678900");
        assertNotNull(paciente);
        assertEquals("Carlos", paciente.getNome());
    }

    @Test
    void deveAdicionarMultiplasConsultasParaMesmoPaciente() {
        service.agendarConsulta("12345678900", "Carlos", "2025-06-30T09:00");
        service.agendarConsulta("12345678900", "Carlos", "2025-07-01T14:00");

        List<Consulta> consultas = service.getConsultas("12345678900");
        assertEquals(2, consultas.size());
        assertEquals("Carlos", consultas.get(0).getPaciente());
        assertEquals("Carlos", consultas.get(1).getPaciente());
    }

    @Test
    void pacienteNaoEncontradoDeveRetornarListaVazia() {
        List<Consulta> consultas = service.getConsultas("00000000000");
        assertNotNull(consultas);
        assertTrue(consultas.isEmpty());
    }
}
