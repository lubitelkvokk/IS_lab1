package itmo.is.lab1.exceptionHandler;

public class MinIOConnectException extends Exception{
    public MinIOConnectException(String message){
        super(message);
    }
    public MinIOConnectException(){
        super("Ошибка подключения к файловому хранилищу");
    }
}
