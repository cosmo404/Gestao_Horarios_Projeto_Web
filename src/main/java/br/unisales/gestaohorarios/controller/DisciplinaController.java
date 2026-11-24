package br.unisales.gestaohorarios.controller;

import br.unisales.gestaohorarios.model.Disciplina;
import br.unisales.gestaohorarios.repository.DisciplinaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
@Tag(name = "Disciplinas", description = "Gestão de Disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaRepository repository;

    @GetMapping
    @Operation(summary = "Listar todas as disciplinas")
    public List<Disciplina> listar() {
        return repository.findAll();
    }

    @PostMapping
    @Operation(summary = "Cadastrar disciplina")
    public ResponseEntity<Disciplina> criar(@Valid @RequestBody Disciplina disciplina) {
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(disciplina));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar disciplina")
    public ResponseEntity<Disciplina> atualizar(@PathVariable Long id, @Valid @RequestBody Disciplina dados) {
        return repository.findById(id).map(d -> {
            d.setNome(dados.getNome());
            d.setCargaHoraria(dados.getCargaHoraria());
            return ResponseEntity.ok(repository.save(d));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir disciplina")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
