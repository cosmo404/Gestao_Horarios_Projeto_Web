package br.unisales.gestaohorarios.controller;

import br.unisales.gestaohorarios.model.Professor;
import br.unisales.gestaohorarios.repository.ProfessorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@Tag(name = "Professores", description = "Gestão de Professores")
public class ProfessorController {

    @Autowired
    private ProfessorRepository repository;

    @GetMapping
    @Operation(summary = "Listar todos os professores")
    public List<Professor> listar() {
        return repository.findAll();
    }

    @PostMapping
    @Operation(summary = "Cadastrar professor")
    public ResponseEntity<Professor> criar(@Valid @RequestBody Professor professor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(professor));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar professor")
    public ResponseEntity<Professor> atualizar(@PathVariable Long id, @Valid @RequestBody Professor dados) {
        return repository.findById(id).map(p -> {
            p.setNome(dados.getNome());
            p.setEmail(dados.getEmail());
            p.setMatricula(dados.getMatricula());
            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir professor")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
