package itmo.is.lab1.DTO.model.data;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AddressDTO {

    private Integer id;
    @Schema(description = "id владельца объекта")
    private Integer userId;
    @NotNull(message = "Address's street name cannot be null")
    @Size(max = 196, message = "Size of address's street name must be less than 197")
    @Schema(description = "Название улицы", requiredMode = Schema.RequiredMode.REQUIRED, example = "Koroleva 21")
    private String street;//Длина строки не должна быть больше 196, Поле может быть null

    @NotNull(message = "zipCode of address cannot be null")
    @Schema(description = "Почтовый индекс", requiredMode = Schema.RequiredMode.REQUIRED, example = "468320")
    private String zipCode;//Поле может быть null
}