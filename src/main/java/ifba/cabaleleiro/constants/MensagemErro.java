package ifba.cabaleleiro.constants;

public class MensagemErro {

    private MensagemErro() {}

    public static final String ERRO_CLIENTE_NAO_ENCONTRADO = "Não foi encontrado cliente com o id: %d";
    public static final String ERRO_BARBEIRO_NAO_ENCONTRADO = "Não foi encontrado barbeiro com o id: %d";
    public static final String ERRO_AGENDAMENTO_NAO_ENCONTRADO = "Não foi encontrado agendamento com o id: %d";
    public static final String ERRO_EMAIL_DUPLICADO = "Já existe um cliente cadastrado com este e-mail.";
    public static final String ERRO_HORARIO_INDISPONIVEL = "Este barbeiro já possui um agendamento nesse dia e horário.";
    public static final String ERRO_CLIENTE_POSSUI_AGENDAMENTO = "Não é possível excluir: este cliente possui agendamentos ativos.";
    public static final String ERRO_BARBEIRO_POSSUI_AGENDAMENTO = "Não é possível excluir: este barbeiro possui agendamentos ativos.";
}