package com.alkemy.ong.domain.organizations;


import com.alkemy.ong.domain.organizations.OrganizationModel;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository {

    List<OrganizationModel> findAll();

    Optional<OrganizationModel> findById(int id);

    OrganizationModel save(OrganizationModel organizationModel);

}
