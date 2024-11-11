package itmo.is.lab1.exceptionHandler;

public class NotEnoughAccessLevelToData extends Exception{
    public NotEnoughAccessLevelToData(String msg){
        super(msg);
    }
}
