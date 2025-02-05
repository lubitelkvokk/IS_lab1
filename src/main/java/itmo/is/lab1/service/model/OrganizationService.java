package itmo.is.lab1.service.model;

import itmo.is.lab1.DTO.model.data.OrganizationDTO;
import itmo.is.lab1.dao.AddressDAO;
import itmo.is.lab1.dao.OrganizationDAO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Address;
import itmo.is.lab1.model.data.Organization;
import itmo.is.lab1.mapper.OrganizationMapper;
import itmo.is.lab1.permission.PermissionChecker;
import itmo.is.lab1.service.auth.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizationService {
    @Autowired
    private UserService userService;
    @Autowired
    private OrganizationDAO organizationDAO;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private OrganizationMapper organizationMapper;
    @Autowired
    private PermissionChecker permissionChecker;

    @Transactional
    public OrganizationDTO createOrganization(OrganizationDTO organizationDTO, User user) throws DbException {
        if (organizationDAO.findOrganizationByFullName(organizationDTO.getFullName()) != null) {
            throw new DbException("Organization with such name exist yet");
        }

        Organization organization = organizationMapper.toEntity(organizationDTO);
        organization.setUser(user);
        // Проверяем наличие Address и загружаем его из базы данных
        Address existingAddress = addressDAO.findById(organizationDTO.getAddressId())
                .orElseThrow(() -> new DbException("Address not found with id: " + organizationDTO.getAddressId()));
        organization.setOfficialAddress(existingAddress);

        Organization savedOrganization = organizationDAO.save(organization);
        return organizationMapper.toDTO(savedOrganization);
    }


    public OrganizationDTO getOrganizationById(Integer id) throws DbException, NotEnoughAccessLevelToData {
        Organization organization = organizationDAO.findById(id).orElseThrow(() ->
                new DbException("Organization not found with id = %d".formatted(id)));

        return organizationMapper.toDTO(organization);
    }

    public void updateOrganization(OrganizationDTO organizationDTO) throws NotEnoughAccessLevelToData, DbException {
        if (organizationDTO.getId() == null) throw new DbException("Id of updated organiation mustn't be null");
        Organization organization = organizationDAO.findById(organizationDTO.getId()).orElseThrow(() ->
                new DbException("Organization not found with id = %d".formatted(organizationDTO.getId())));

        // Проверка доступа
        permissionChecker.checkRUDPermission(organization);
        // Обновление полей
        organization.setFullName(organizationDTO.getFullName());
        organization.setAnnualTurnover(organizationDTO.getAnnualTurnover());
        organization.setEmployeesCount(organizationDTO.getEmployeesCount());
        organization.setRating(organizationDTO.getRating());
        organization.setType(organizationDTO.getType());

        // Проверяем наличие Address и загружаем его из базы данных
        if (organizationDTO.getAddressId() != null) {
            Address existingAddress = addressDAO.findById(organizationDTO.getAddressId())
                    .orElseThrow(() -> new DbException("Address not found with id: " + organizationDTO.getAddressId()));
            organization.setOfficialAddress(existingAddress);
        }
        organizationDAO.save(organization);
    }

    public void deleteOrganization(Integer id, User user) throws DbException, NotEnoughAccessLevelToData {
        Organization organization = organizationDAO.findById(id).orElseThrow(() ->
                new DbException("Organization not found with id = %d".formatted(id)));

        // Проверка доступа
        permissionChecker.checkRUDPermission(organization);
        organizationDAO.delete(organization);
    }

    public Page<OrganizationDTO> getNOrganizationStartFromPage(Pageable pageable) {
        return organizationDAO.findAll(pageable).map(organizationMapper::toDTO);
    }
}
