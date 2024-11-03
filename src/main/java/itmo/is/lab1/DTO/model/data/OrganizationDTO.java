package itmo.is.lab1.DTO.model.data;

import itmo.is.lab1.model.data.Address;
import itmo.is.lab1.model.data.OrganizationType;
import lombok.Data;

@Data
public class OrganizationDTO {
    private Address officialAddress; //Поле не может быть null
    private Integer annualTurnover; //Поле может быть null, Значение поля должно быть больше 0
    private long employeesCount; //Значение поля должно быть больше 0
    private String fullName; //Длина строки не должна быть больше 1272, Строка не может быть пустой, Поле может быть null
    private double rating; //Значение поля должно быть больше 0
    private OrganizationType type; //Поле может быть null
}