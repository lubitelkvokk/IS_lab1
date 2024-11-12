package itmo.is.lab1.service;

import io.jsonwebtoken.ExpiredJwtException;
import itmo.is.lab1.DTO.model.data.AddressDTO;
import itmo.is.lab1.dao.AddressDAO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Address;
import itmo.is.lab1.objMapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AddressService {
    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private AddressMapper addressMapper;

    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = addressMapper.toEntity(addressDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        address.setUser((User) authentication.getPrincipal());
        return addressMapper.toDTO(addressDAO.save(address));
    }

    public AddressDTO getAddressById(Integer id) throws NotEnoughAccessLevelToData, DbException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User customUser) {
            Address address = addressDAO.findById(id).orElseThrow(() ->
                    new DbException("Have no address with id = %d".formatted(id)));
//            if (!Objects.equals(address.getUser().getId(), customUser.getId())) {
//                throw new NotEnoughAccessLevelToData(
//                        "An attempt to change someone else's data");
//            }
            return addressMapper.toDTO(address);
        } else {
            throw new NotEnoughAccessLevelToData("Have no rights");
        }

    }

    public void updateAddress(AddressDTO addressDTO) throws NotEnoughAccessLevelToData {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User customUser) {
            Address address = addressDAO.findById(addressDTO.getId()).orElseThrow();
            if (!Objects.equals(address.getUser().getId(), customUser.getId())) {
                throw new NotEnoughAccessLevelToData(
                        "An attempt to change someone else's data");
            }
            address.setStreet(addressDTO.getStreet());
            address.setZipCode(addressDTO.getZipCode());
            addressDAO.save(address);
        } else {
            throw new NotEnoughAccessLevelToData("Have no rights");
        }

    }

    public void deleteAddress(AddressDTO addressDTO) throws NotEnoughAccessLevelToData {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal() instanceof User customUser) {
            Address address = addressDAO.findById(addressDTO.getId()).orElseThrow();
            if (!Objects.equals(address.getUser().getId(), customUser.getId())) {
                throw new NotEnoughAccessLevelToData(
                        "An attempt to change someone else's data");
            }
            addressDAO.delete(address);
        } else {
            throw new NotEnoughAccessLevelToData("Have no rights");
        }
    }
}
