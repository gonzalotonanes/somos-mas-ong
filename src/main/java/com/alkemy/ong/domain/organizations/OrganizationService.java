package com.alkemy.ong.domain.organizations;


import java.util.List;

public interface OrganizationService {

    List<OrganizationModel> getAllOrganization();

    OrganizationModel update(OrganizationModel organizationModel);

}
