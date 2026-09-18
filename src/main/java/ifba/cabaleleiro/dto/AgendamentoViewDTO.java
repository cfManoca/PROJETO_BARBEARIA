package ifba.cabaleleiro.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoViewDTO {

    private Long id;
    private String cliente;
    private String barbeiro;
    private String servico;
    private LocalTime horario;
    private String status;
    private String statusCss;

}