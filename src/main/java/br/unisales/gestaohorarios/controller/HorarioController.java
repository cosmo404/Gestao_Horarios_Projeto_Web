package br.unisales.gestaohorarios.controller;

import br.unisales.gestaohorarios.model.Horario;
import br.unisales.gestaohorarios.repository.HorarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@Tag(name = "Horários", description = "Gestão de Alocação de Horários")
public class HorarioController {

    @Autowired
    private HorarioRepository repository;

    @GetMapping
    @Operation(summary = "Listar todos os horários cadastrados")
    public List<Horario> listar() {
        return repository.findAll();
    }

    @PostMapping
    @Operation(summary = "Cadastrar um novo horário")
    public ResponseEntity<Horario> criar(@Valid @RequestBody Horario horario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(horario));
    }

    @GetMapping("/professor/{professorId}")
    @Operation(summary = "Visualizar horários, turmas e disciplinas de um Professor")
    public List<Horario> buscarPorProfessor(@PathVariable Long professorId) {
        return repository.findByProfessorId(professorId);
    }

    @GetMapping("/turma/{turmaId}")
    @Operation(summary = "Visualizar horários, professores e disciplinas de uma Turma")
    public List<Horario> buscarPorTurma(@PathVariable Long turmaId) {
        return repository.findByTurmaId(turmaId);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir horário")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
