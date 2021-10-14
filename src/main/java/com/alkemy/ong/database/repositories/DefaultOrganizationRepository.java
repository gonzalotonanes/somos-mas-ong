package com.alkemy.ong.database.repositories;

import com.alkemy.ong.database.entities.OrganizationEntity;
import com.alkemy.ong.database.jparepositories.OrganizationJPARepository;
import com.alkemy.ong.domain.organizations.OrganizationDomainException;
import com.alkemy.ong.domain.organizations.OrganizationModel;
import com.alkemy.ong.domain.organizations.OrganizationRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
public class DefaultOrganizationRepository implements OrganizationRepository {
    OrganizationJPARepository jpaRepository;

    public DefaultOrganizationRepository(OrganizationJPARepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    public List<OrganizationModel> findAll() {
        return jpaRepository.findAll().stream()
                .map(o -> toModel(o))
                .collect(toList());
    }

    @Override
    public Optional<OrganizationModel> findById(int id) {
        Optional<OrganizationEntity> organizationEntity = jpaRepository.findById(id);
        return organizationEntity.isPresent()
                ? Optional.of(toModel(organizationEntity.get()))
                : (Optional<OrganizationModel>) Optional.empty().orElseThrow(() -> new OrganizationDomainException());
    }

    @Override
    public OrganizationModel save(OrganizationModel organizationModel) {
        return toModel(jpaRepository.save(toEntity(organizationModel)));
    }

    public OrganizationModel toModel(OrganizationEntity organization) {
        OrganizationModel organizationModel = new OrganizationModel();
        organizationModel.setId(organization.getId());
        organizationModel.setName(organization.getName());
        organizationModel.setImage(organization.getImage());
        organizationModel.setAddress(organization.getAddress());
        organizationModel.setPhone(organization.getPhone());
        organizationModel.setEmail(organization.getEmail());
        organizationModel.setWelcomeText(organization.getWelcomeText());
        organizationModel.setAboutUsText(organization.getAboutUsText());
        organizationModel.setCreatedAt(organization.getCreatedAt());
        organizationModel.setUpdatedAt(organization.getUpdatedAt());
        organizationModel.setUrlFacebook(organization.getUrlFacebook());
        organizationModel.setUrlLinkedin(organization.getUrlLinkedin());
        organizationModel.setUrlInstagram(organization.getUrlInstagram());
        return organizationModel;
    }

    public OrganizationEntity toEntity(OrganizationModel organization) {
        OrganizationEntity organizationEntity = new OrganizationEntity(
                organization.getId(),
                organization.getName(),
                organization.getImage(),
                organization.getAddress(),
                organization.getPhone(),
                organization.getEmail(),
                organization.getWelcomeText(),
                organization.getAboutUsText(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                organization.isDeleted(),
                organization.getUrlFacebook(),
                organization.getUrlLinkedin(),
                organization.getUrlInstagram());

        return organizationEntity;
    }
}
