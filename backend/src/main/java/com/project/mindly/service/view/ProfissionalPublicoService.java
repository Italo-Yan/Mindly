package com.project.mindly.service.view;


import com.project.mindly.model.view.ProfissionalPublicoView;
import com.project.mindly.repository.view.ProfissionalPublicoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalPublicoService {

    private final ProfissionalPublicoRepository profissionalPublicoRepository;


    public ProfissionalPublicoService(ProfissionalPublicoRepository profissionalPublicoRepository) {
        this.profissionalPublicoRepository = profissionalPublicoRepository;
    }

    public List<ProfissionalPublicoView> findProfissionalPublicoAll() {
        return profissionalPublicoRepository.findAll();
    }

    public Optional<ProfissionalPublicoView> findProfissionalPublicoByCrp(String crp) {
        return Optional.ofNullable(profissionalPublicoRepository.findById(crp)
                .orElseThrow(()-> new EntityNotFoundException("Profissional não encontrado com  o CRP: "+ crp)));
    }

    public Optional<List<ProfissionalPublicoView>> findProfissionalPublicoByName(String nome) {
        List<ProfissionalPublicoView> profissionais = profissionalPublicoRepository.findByNomeProf(nome);
        return profissionais.isEmpty() ? Optional.empty() : Optional.of(profissionais);
    }
}
