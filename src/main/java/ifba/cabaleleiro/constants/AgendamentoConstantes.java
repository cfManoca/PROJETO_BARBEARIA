package ifba.cabaleleiro.constants;

import java.util.List;

public class AgendamentoConstantes {
    private AgendamentoConstantes() {}

    public static final Long BARBEIRO_PADRAO_ID = 1L;

    public static final List<String> HORARIOS_DISPONIVEIS = List.of(
        "08:00", "09:00", "10:00", "11:00",
        "13:00", "14:00", "15:00", "16:00", "17:00", "18:00"
    );
}