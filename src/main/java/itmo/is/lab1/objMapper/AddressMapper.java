package itmo.is.lab1.objMapper;

import itmo.is.lab1.DTO.model.data.AddressDTO;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements GenericMapper<Address, AddressDTO> {

    @Override
    public AddressDTO toDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setUserId(address.getUser().getId());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setZipCode(address.getZipCode());
        return addressDTO;
    }

    @Override
    public Address toEntity(AddressDTO addressDTO) {
        if (addressDTO == null) {
            return null;
        }
        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setZipCode(addressDTO.getZipCode());
        return address;
    }
}

