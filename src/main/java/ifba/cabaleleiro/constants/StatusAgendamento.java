package ifba.cabaleleiro.constants;

public class StatusAgendamento {
    private StatusAgendamento() {}

    public static final String AGENDADO = "Agendado";
    public static final String CONFIRMADO = "Confirmado";
    public static final String CONCLUIDO = "Concluído";
    public static final String CANCELADO = "Cancelado";

    public static String paraCss(String status) {
        if (AGENDADO.equals(status)) return "agendado";
        if (CONFIRMADO.equals(status)) return "confirmado";
        if (CONCLUIDO.equals(status)) return "concluido";
        if (CANCELADO.equals(status)) return "cancelado";
        return "agendado";
    }
}