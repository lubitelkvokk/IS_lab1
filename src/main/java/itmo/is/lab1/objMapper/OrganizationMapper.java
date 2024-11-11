package itmo.is.lab1.objMapper;

import itmo.is.lab1.DTO.model.data.OrganizationDTO;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Organization;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper implements GenericMapper<Organization, OrganizationDTO> {

    @Override
    public OrganizationDTO toDTO(Organization organization) {
        if (organization == null) {
            return null;
        }
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId(organization.getId());
        organizationDTO.setUserId(organization.getUser().getId());
        organizationDTO.setOfficialAddress(organization.getOfficialAddress());
        organizationDTO.setAnnualTurnover(organization.getAnnualTurnover());
        organizationDTO.setEmployeesCount(organization.getEmployeesCount());
        organizationDTO.setFullName(organization.getFullName());
        organizationDTO.setRating(organization.getRating());
        organizationDTO.setType(organization.getType());
        return organizationDTO;
    }

    @Override
    public Organization toEntity(OrganizationDTO organizationDTO) {
        if (organizationDTO == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(organizationDTO.getId());
        User user = new User();
        user.setId(organizationDTO.getUserId());
        organization.setUser(user);
        organization.setOfficialAddress(organizationDTO.getOfficialAddress());
        organization.setAnnualTurnover(organizationDTO.getAnnualTurnover());
        organization.setEmployeesCount(organizationDTO.getEmployeesCount());
        organization.setFullName(organizationDTO.getFullName());
        organization.setRating(organizationDTO.getRating());
        organization.setType(organizationDTO.getType());
        return organization;
    }
}
