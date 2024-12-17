package itmo.is.lab1.mapper;

import itmo.is.lab1.DTO.model.data.OrganizationDTO;
import itmo.is.lab1.model.auth.User;
import itmo.is.lab1.model.data.Address;
import itmo.is.lab1.model.data.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrganizationMapper implements GenericMapper<Organization, OrganizationDTO> {
    @Autowired
    private AddressMapper addressMapper;

    @Override
    public OrganizationDTO toDTO(Organization organization) {
        if (organization == null) {
            return null;
        }
        OrganizationDTO organizationDTO = new OrganizationDTO();
        organizationDTO.setId(organization.getId());
        organizationDTO.setUserId(organization.getUser().getId());
        organizationDTO.setAddressId(organization.getOfficialAddress().getId());
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
        Address address = new Address();
        address.setId(organizationDTO.getAddressId());
        organization.setOfficialAddress(address);
        organization.setAnnualTurnover(organizationDTO.getAnnualTurnover());
        organization.setEmployeesCount(organizationDTO.getEmployeesCount());
        organization.setFullName(organizationDTO.getFullName());
        organization.setRating(organizationDTO.getRating());
        organization.setType(organizationDTO.getType());
        return organization;
    }
}
