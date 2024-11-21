package itmo.is.lab1.service;

import itmo.is.lab1.DTO.model.data.AddressDTO;
import itmo.is.lab1.dao.AddressDAO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Address;
import itmo.is.lab1.objMapper.AddressMapper;
import itmo.is.lab1.permission.PermissionChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private PermissionChecker permissionChecker;

    public AddressDTO createAddress(AddressDTO addressDTO) {
        Address address = addressMapper.toEntity(addressDTO);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        address.setUser((User) authentication.getPrincipal());
        return addressMapper.toDTO(addressDAO.save(address));
    }

    public AddressDTO getAddressById(Integer id) throws NotEnoughAccessLevelToData, DbException {
        Address address = addressDAO.findById(id).orElseThrow(() ->
                new DbException("Have no address with id = %d".formatted(id)));
        return addressMapper.toDTO(address);
    }

    public void updateAddress(AddressDTO addressDTO) throws NotEnoughAccessLevelToData, DbException {
        Address address = addressDAO.findById(addressDTO.getId()).orElseThrow(() -> new DbException("Such id not found in addresses table"));
        permissionChecker.checkRUDPermission(address);
        address.setStreet(addressDTO.getStreet());
        address.setZipCode(addressDTO.getZipCode());
        addressDAO.save(address);
    }

    public void deleteAddress(Integer id) throws NotEnoughAccessLevelToData {
        Address address = addressDAO.findById(id).orElseThrow();
        permissionChecker.checkRUDPermission(address);
        addressDAO.delete(address);
    }

    public Page<AddressDTO> getNAddressesStartFromPage(Pageable pageable, String searchStreet) {
        if (searchStreet == null || searchStreet.isBlank() || searchStreet.isEmpty())
            return addressDAO.findAll(pageable).map(addressMapper::toDTO);
        return addressDAO.findAllByStreet(pageable, searchStreet.trim()).map(addressMapper::toDTO);
    }
}
