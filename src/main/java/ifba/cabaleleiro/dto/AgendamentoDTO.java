package ifba.cabaleleiro.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgendamentoDTO {

    private Long id;

    @NotNull
    private Long clienteId;

    @NotNull
    private Long barbeiroId;

    @NotBlank
    @Size(max = 100)
    private String servico;

    @NotNull
    @FutureOrPresent
    private LocalDate data;

    @NotNull
    private LocalTime horario;

    @NotBlank
    @Size(max = 20)
    private String status;

}
