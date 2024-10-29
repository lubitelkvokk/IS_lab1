package itmo.is.lab1.model;

public class Worker {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.util.Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Organization organization; //Поле не может быть null
    private long salary; //Значение поля должно быть больше 0
    private Integer rating; //Поле может быть null, Значение поля должно быть больше 0
    private java.time.LocalDateTime endDate; //Поле может быть null
    private Position position; //Поле не может быть null
    private Status status; //Поле не может быть null
    private Person person; //Поле не может быть null
}