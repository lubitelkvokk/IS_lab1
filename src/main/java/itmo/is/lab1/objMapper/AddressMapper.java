package itmo.is.lab1.objMapper;

import itmo.is.lab1.DTO.model.data.AddressDTO;
import itmo.is.lab1.model.data.Address;

public class AddressMapper implements GenericMapper<Address, AddressDTO> {

    @Override
    public AddressDTO toDTO(Address address) {
        if (address == null) {
            return null;
        }
        AddressDTO addressDTO = new AddressDTO();
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

