package itmo.is.lab1.obj_mapper;

public interface GenericMapper<E, D> {
    D toDTO(E entity); // Преобразование из Entity в DTO
    E toEntity(D dto); // Преобразование из DTO в Entity
}
