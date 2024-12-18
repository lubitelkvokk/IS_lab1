package itmo.is.lab1.exceptionHandler;

public class ImportFormatException extends Exception {
    public ImportFormatException() {
        super("Неверный формат импортируемого файла");
    }
    public ImportFormatException(String message) {
        super(message);
    }
}
