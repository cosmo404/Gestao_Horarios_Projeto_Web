package br.unisales.gestaohorarios.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.time.LocalTime;

@Entity
@Table(name = "tb_horarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O dia da semana é obrigatório")
    private String diaSemana;

    @NotNull(message = "O horário de início é obrigatório")
    private LocalTime horaInicio;

    @NotNull(message = "O horário de término é obrigatório")
    private LocalTime horaFim;

    private String sala;

    @NotNull(message = "O professor é obrigatório")
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @NotNull(message = "A disciplina é obrigatória")
    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private Disciplina disciplina;

    @NotNull(message = "A turma é obrigatória")
    @ManyToOne
    @JoinColumn(name = "turma_id", nullable = false)
    private Turma turma;
}
