package br.unisales.gestaohorarios.controller;

import br.unisales.gestaohorarios.model.Turma;
import br.unisales.gestaohorarios.repository.TurmaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
@Tag(name = "Turmas", description = "Gestão de Turmas")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @GetMapping
    @Operation(summary = "Listar todas as turmas")
    public List<Turma> listar() {
        return repository.findAll();
    }

    @PostMapping
    @Operation(summary = "Cadastrar turma")
    public ResponseEntity<Turma> criar(@Valid @RequestBody Turma turma) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(turma));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar turma")
    public ResponseEntity<Turma> atualizar(@PathVariable Long id, @Valid @RequestBody Turma dados) {
        return repository.findById(id).map(t -> {
            t.setNome(dados.getNome());
            t.setSemestre(dados.getSemestre());
            t.setTurno(dados.getTurno());
            return ResponseEntity.ok(repository.save(t));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir turma")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
