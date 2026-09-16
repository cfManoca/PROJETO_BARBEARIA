package ifba.cabaleleiro.exception;

import lombok.Getter;

@Getter
public class AppCabeleleiroException extends RuntimeException {

    private int status;

    public AppCabeleleiroException(String msg) {
        super(msg);
        this.status = 400;
    }

    public AppCabeleleiroException(String msg, int status) {
        super(msg);
        this.status = status;
    }
}