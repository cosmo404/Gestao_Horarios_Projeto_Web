package br.unisales.gestaohorarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "tb_turmas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da turma é obrigatório")
    @Column(unique = true)
    private String nome;

    @NotBlank(message = "O curso é obrigatório")
    private String curso;

    @NotBlank(message = "O semestre é obrigatório")
    private String semestre;

    @NotBlank(message = "O turno é obrigatório")
    private String turno;
}