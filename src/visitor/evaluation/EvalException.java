package visitor.evaluation;

/**
 * Created by marco on 16/08/16.
 */
public class EvalException extends RuntimeException {

    public EvalException() {
    }

    public EvalException(String message) {
        super(message);
    }

    public EvalException(Throwable cause) {
        super(cause);
    }

    public EvalException(String message, Throwable cause) {
        super(message, cause);
    }
}
