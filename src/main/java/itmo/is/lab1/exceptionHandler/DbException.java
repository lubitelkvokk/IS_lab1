package itmo.is.lab1.exceptionHandler;

public class DbException extends Exception{
    public DbException(){
       super();
    }

    public DbException(String msg){
        super(msg);
    }
}
