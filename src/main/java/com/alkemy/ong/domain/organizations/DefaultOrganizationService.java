package com.alkemy.ong.domain.organizations;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DefaultOrganizationService implements OrganizationService {

    private OrganizationRepository organizationRepository;

    public DefaultOrganizationService(OrganizationRepository organizationRepository) {
        this.organizationRepository = organizationRepository;
    }

    @Override
    public List<OrganizationModel> getAllOrganization() {
        return organizationRepository.findAll();
    }

    @Override
    public OrganizationModel update(OrganizationModel organizationModel) {
        OrganizationModel organizationModelUpdate = organizationRepository.findById(organizationModel.getId()).orElseThrow(RuntimeException::new);
        organizationModelUpdate.setName(organizationModel.getName());
        organizationModelUpdate.setImage(organizationModel.getImage());
        organizationModelUpdate.setPhone(organizationModel.getPhone());
        organizationModelUpdate.setAddress(organizationModel.getAddress());
        organizationModelUpdate.setUpdatedAt(LocalDateTime.now());
        organizationModelUpdate.setUrlFacebook(organizationModel.getUrlFacebook());
        organizationModelUpdate.setUrlLinkedin(organizationModel.getUrlLinkedin());
        organizationModelUpdate.setUrlInstagram(organizationModel.getUrlInstagram());
        return organizationRepository.save(organizationModelUpdate);
    }
}
