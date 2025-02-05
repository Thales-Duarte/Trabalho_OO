package main.controller;

import main.entities.*;
import main.excecoes.*;
import main.services.Consulta;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SistemaGerenciamentoClinica {

    private List<Paciente> pacientes = new ArrayList<>();
    private List<Medico> medicos = new ArrayList<>();

    public void cadastrarPaciente(Paciente novoPaciente) throws PacienteJaCadastradoException, ConsultaDataInvalidaException {
        for (Paciente p : pacientes) {
            if (p.getCpf().equals(novoPaciente.getCpf())) {
                throw new PacienteJaCadastradoException("Paciente com CPF " + novoPaciente.getCpf() + " já está cadastrado.");
            }
            
        }
        pacientes.add(novoPaciente);
    }

    public void cadastrarMedico(Medico novoMedico) throws MedicoJaCadastradoException {
        for (Medico m : medicos) {
            if (m.getCpf().equals(novoMedico.getCpf()) || m.getCrm().equals(novoMedico.getCrm())) {
                throw new MedicoJaCadastradoException("Médico com CPF " + novoMedico.getCpf() + " ou CRM " + novoMedico.getCrm() + " já está cadastrado.");
            }
        }
        medicos.add(novoMedico);
    }

    public void agendarConsulta(Consulta consulta, List<Consulta> consultasExistentes) 
            throws HorarioIndisponivelException, PagamentoPendenteException, ConsultaForaDoHorarioException, ConsultaDataInvalidaException {
        
        if (consulta.getHorarioInicio().isBefore(LocalTime.of(8, 0)) || 
            consulta.getHorarioInicio().isAfter(LocalTime.of(18, 0))) {
            throw new ConsultaForaDoHorarioException("Consulta fora do horário de expediente (8h às 18h).");
        }
        
        if (consulta.getDataConsulta().isBefore(LocalDate.now())) {
           throw new ConsultaDataInvalidaException(".");
        }
        
        for (Consulta c : consultasExistentes) {
            if (c.getDataConsulta().equals(consulta.getDataConsulta()) &&
                c.getHorarioInicio().equals(consulta.getHorarioInicio()) &&
                c.getMedico().equals(consulta.getMedico())) {
                throw new HorarioIndisponivelException("O médico já possui uma consulta neste horário.");
            }
        }

        if (consulta.getPaciente().isPossuiPagamentoPendente()) {
            throw new PagamentoPendenteException("O paciente possui pagamentos pendentes.");
        }

        consultasExistentes.add(consulta);
        consulta.getPaciente().adicionarConsulta(consulta);
        consulta.getMedico().adicionarConsulta(consulta);
    }


public List<Paciente> getPacientes() {
    return pacientes;
}

public List<Medico> getMedicos() {
    return medicos;
}

}