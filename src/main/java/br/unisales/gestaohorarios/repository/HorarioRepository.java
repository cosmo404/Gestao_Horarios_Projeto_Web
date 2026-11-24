package br.unisales.gestaohorarios.repository;

import br.unisales.gestaohorarios.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    // Métodos de consulta personalizados exigidos no trabalho
    List<Horario> findByProfessorId(Long professorId);

    List<Horario> findByTurmaId(Long turmaId);
}
