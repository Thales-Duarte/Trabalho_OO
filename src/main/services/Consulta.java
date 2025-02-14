package main.services;

import main.entities.*;
import main.enums.Status;

import java.time.LocalDate;
import java.time.LocalTime;

public class Consulta {
    private LocalDate dataConsulta;
    private LocalTime horarioInicio;
    private int duracaoMinutos;
    private Status status;
    private Medico medico;
    private Paciente paciente;

    public Consulta(LocalDate dataConsulta, LocalTime horarioInicio, int duracaoMinutos, Status status, Medico medico, Paciente paciente) {
        this.dataConsulta = dataConsulta;
        this.horarioInicio = horarioInicio;
        this.duracaoMinutos = duracaoMinutos;
        this.status = status;
        this.medico = medico;
        this.paciente = paciente;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public LocalTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public void setDuracaoMinutos(int duracaoMinutos) {
        this.duracaoMinutos = duracaoMinutos;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    
    
    @Override
    public String toString() {
        return "Consulta{" +
                "dataConsulta=" + dataConsulta +
                ", horarioInicio=" + horarioInicio +
                ", duracaoMinutos=" + duracaoMinutos +
                ", status='" + status + '\'' +
                ", medico=" + medico.getNome() +
                ", paciente=" + paciente.getNome() +
                '}';
    }
}