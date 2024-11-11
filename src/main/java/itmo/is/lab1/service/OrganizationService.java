package itmo.is.lab1.service;

import itmo.is.lab1.DTO.model.data.OrganizationDTO;
import itmo.is.lab1.dao.OrganizationDAO;
import itmo.is.lab1.exceptionHandler.DbException;
import itmo.is.lab1.exceptionHandler.NotEnoughAccessLevelToData;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Organization;
import itmo.is.lab1.objMapper.OrganizationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationDAO organizationDAO;

    @Autowired
    private OrganizationMapper organizationMapper;

    public OrganizationDTO createOrganization(OrganizationDTO organizationDTO, User user) {
        organizationDTO.setUserId(user.getId());
        Organization organization = organizationMapper.toEntity(organizationDTO);
        organization.setUser(user);
        Organization savedOrganization = organizationDAO.save(organization);
        return organizationMapper.toDTO(savedOrganization);
    }


    public OrganizationDTO getOrganizationById(Integer id, User user) throws DbException, NotEnoughAccessLevelToData {
        Organization organization = organizationDAO.findById(id).orElseThrow(() ->
                new DbException("Organization not found with id = %d".formatted(id)));

        // Проверка доступа
        if (!Objects.equals(organization.getUser().getId(), user.getId())) {
            throw new NotEnoughAccessLevelToData("Attempt to access someone else's data");
        }

        return organizationMapper.toDTO(organization);
    }

    public void updateOrganization(OrganizationDTO organizationDTO, User user) throws NotEnoughAccessLevelToData, DbException {
        Organization organization = organizationDAO.findById(organizationDTO.getId()).orElseThrow(() ->
                new DbException("Organization not found with id = %d".formatted(organizationDTO.getId())));

        // Проверка доступа
        if (!Objects.equals(organization.getUser().getId(), user.getId())) {
            throw new NotEnoughAccessLevelToData("Attempt to modify someone else's data");
        }

        // Обновление полей
        organization.setFullName(organizationDTO.getFullName());
        organization.setAnnualTurnover(organizationDTO.getAnnualTurnover());
        organization.setEmployeesCount(organizationDTO.getEmployeesCount());
        organization.setRating(organizationDTO.getRating());
        organization.setType(organizationDTO.getType());
        organization.setOfficialAddress(organizationDTO.getOfficialAddress());

        organizationDAO.save(organization);
    }

    public void deleteOrganization(Integer id, User user) throws DbException, NotEnoughAccessLevelToData {
        Organization organization = organizationDAO.findById(id).orElseThrow(() ->
                new DbException("Organization not found with id = %d".formatted(id)));

        // Проверка доступа
        if (!Objects.equals(organization.getUser().getId(), user.getId())) {
            throw new NotEnoughAccessLevelToData("Attempt to delete someone else's data");
        }

        organizationDAO.delete(organization);
    }
}
