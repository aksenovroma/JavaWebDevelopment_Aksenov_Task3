package by.epam.javatraning.aksenov.task1.model.exception.technical;

public class QueueIsEmptyException extends TechnicalProjectException {
    public QueueIsEmptyException() {
    }

    public QueueIsEmptyException(String message) {
        super(message);
    }

    public QueueIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }

    public QueueIsEmptyException(Throwable cause) {
        super(cause);
    }
}
