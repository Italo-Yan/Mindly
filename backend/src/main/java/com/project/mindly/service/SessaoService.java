package com.project.mindly.service;

import com.project.mindly.model.agendamento.Agendamento;
import com.project.mindly.model.agendamento.AgendamentoDtoResponse;
import com.project.mindly.model.paciente.Paciente;
import com.project.mindly.model.profissional.Profissional;
import com.project.mindly.model.sessao.Sessao;
import com.project.mindly.model.sessao.SessaoDto;
import com.project.mindly.model.sessao.SessaoDtoResponse;
import com.project.mindly.repository.AgendamentoRepository;
import com.project.mindly.repository.PacienteRepository;
import com.project.mindly.repository.ProfissionalRepository;
import com.project.mindly.repository.SessaoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SessaoService {

    private final SessaoRepository sessaoRepository;
    private final PacienteRepository pacienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final AgendamentoRepository agendamentoRepository;

    @Autowired
    public SessaoService(SessaoRepository sessaoRepository,
                         PacienteRepository pacienteRepository,
                         ProfissionalRepository profissionalRepository, AgendamentoRepository agendamentoRepository) {
        this.sessaoRepository = sessaoRepository;
        this.pacienteRepository = pacienteRepository;
        this.profissionalRepository = profissionalRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    public List<SessaoDtoResponse> findAllSessao() {
        List<Sessao> sessao = sessaoRepository.findAll();
        return sessao.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Optional<Sessao> findSessaoById(int id) {
        return Optional.ofNullable(sessaoRepository.findById(id))
                .orElseThrow(() -> new EntityNotFoundException("Sessao não encontrada com o ID: " + id));
    }

    public Sessao saveSessao(SessaoDto data) {

        Paciente cpfPaci = pacienteRepository.findByCpfPaciente(data.cpf_paciente());
        if (cpfPaci == null) {
            throw new EntityNotFoundException("Paciente não encontrado com CPF: " + data.cpf_paciente());
        }

        Profissional cpfProf = profissionalRepository.findByCpfProf(data.cpf_prof());
        if (cpfProf == null) {
            throw new EntityNotFoundException("Paciente não encontrado com CPF: " + data.cpf_paciente());
        }

        Agendamento idAgenda = agendamentoRepository.findByIdAgendamento(data.id_agendamento());
        if (idAgenda == null) {
            throw new EntityNotFoundException("Paciente não encontrado com CPF: " + data.id_agendamento());
        }

        Sessao sessao = new Sessao();
        sessao.setAgendamentoSessao(idAgenda);
        sessao.setCpfProfSessao(cpfProf);
        sessao.setCpfPacienteSessao(cpfPaci);
        sessao.setDataSessao(data.data_sessao());
        sessao.setQnt_total(data.quantidade_total());
        return sessaoRepository.save(sessao);
    }

    public Sessao updateSessao(int id, SessaoDto data) {

        Paciente cpfPaci = pacienteRepository.findById(data.cpf_paciente())
                .orElseThrow(() -> new EntityNotFoundException("Paciente não encontrado com CPF: " + data.cpf_paciente()));
        Profissional cpfProf = profissionalRepository.findById(data.cpf_prof())
                .orElseThrow(() -> new EntityNotFoundException("Profissional não encontrado com CPF: " + data.cpf_prof()));
        Agendamento idAgenda = agendamentoRepository.findById(data.id_agendamento())
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com ID: " + data.id_agendamento()));
        return sessaoRepository.findById(id)
                .map(result -> {
                    result.setAgendamentoSessao(idAgenda);
                    result.setCpfProfSessao(cpfProf);
                    result.setCpfPacienteSessao(cpfPaci);
                    result.setDataSessao(data.data_sessao());
                    result.setQnt_total(data.quantidade_total());
                    return sessaoRepository.save(result);
                })
                .orElseThrow(() -> new EntityNotFoundException("Sessao não encontrado com o ID: " + id));
    }


    public void deleteSessao(int id) {
        Sessao sessao = sessaoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Agendamento não encontrado com ID: " + id));
        sessaoRepository.delete(sessao);
    }

    private SessaoDtoResponse convertToDto(Sessao savedSessao) {

        SessaoDtoResponse responseDto = new SessaoDtoResponse();
        responseDto.setId(savedSessao.getId());
        responseDto.setDataSessao(savedSessao.getDataSessao());
        responseDto.setQuantidadeTotal(savedSessao.getQnt_total());
        responseDto.setCpfPaciente(savedSessao.getCpfPacienteSessao().getCpfPaciente());
        responseDto.setNomePaciente(savedSessao.getCpfPacienteSessao().getNomePaciente());
        responseDto.setNomeProfissional(savedSessao.getCpfProfSessao().getNomeProf());
        responseDto.setCpfProfissional(savedSessao.getCpfProfSessao().getCpfProf());

        AgendamentoDtoResponse agendamentoDto = new AgendamentoDtoResponse();
        agendamentoDto.setIdAgendamento(savedSessao.getAgendamentoSessao().getIdAgendamento());
        agendamentoDto.setDataAgendamento(savedSessao.getAgendamentoSessao().getDataAgendamento());
        agendamentoDto.setHoraInicio(savedSessao.getAgendamentoSessao().getHoraInicio());
        agendamentoDto.setDuracao(savedSessao.getAgendamentoSessao().getDuracao());
        agendamentoDto.setObservacoes(savedSessao.getAgendamentoSessao().getObservacoes());
        agendamentoDto.setStatus(savedSessao.getAgendamentoSessao().getStatus());

        responseDto.setAgendamento(agendamentoDto);
        return responseDto;
    }
}
