package com.project.mindly.repository.view;

import com.project.mindly.model.view.AgendaProfissionalView;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaProfissionalRepository extends JpaRepository<AgendaProfissionalView,String> {
}
