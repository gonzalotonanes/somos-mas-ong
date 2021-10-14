package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.organizations.OrganizationModel;
import com.alkemy.ong.domain.organizations.OrganizationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import lombok.AllArgsConstructor;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/organization")
public class OrganizationController {
    OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping("/")
    public List<OrganizationModel> getAll() {
        return organizationService.getAllOrganization();
    }

    @GetMapping("/public")
    public List<OrganizationPublicDTO> getPublicInfo() {
        return organizationService.getAllOrganization().stream()
                .map(o -> toPublicDTO(o))
                .collect(toList());
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping("/public")
    public ResponseEntity<OrganizationDTO> updatePublicData(@RequestBody OrganizationDTO dto) {
        return ResponseEntity.ok(toDTO(organizationService.update(toModel(dto))));
    }

    @Data
    @AllArgsConstructor
    static class OrganizationDTO {
        @JsonIgnore
        private int id;
        private String urlFacebook;
        private String urlLinkedin;
        private String urlInstagram;
    }

    @Data
    @AllArgsConstructor
    static class OrganizationPublicDTO {
        private String urlFacebook;
        private String urlLinkedin;
        private String urlInstagram;
    }

    private OrganizationPublicDTO toPublicDTO(OrganizationModel organizationModel) {
        return new OrganizationPublicDTO(
                organizationModel.getUrlFacebook(),
                organizationModel.getUrlLinkedin(),
                organizationModel.getUrlInstagram());
    }

    private OrganizationDTO toDTO(OrganizationModel organizationModel) {
        return new OrganizationDTO(
                organizationModel.getId(),
                organizationModel.getUrlFacebook(),
                organizationModel.getUrlLinkedin(),
                organizationModel.getUrlInstagram());
    }

    public OrganizationModel toModel(OrganizationDTO organizationDto) {
        OrganizationModel organizationModel = new OrganizationModel();
       organizationModel.setId(organizationDto.getId());
        organizationModel.setUrlFacebook(organizationDto.getUrlFacebook());
        organizationModel.setUrlLinkedin(organizationDto.getUrlLinkedin());
        organizationModel.setUrlInstagram(organizationDto.getUrlInstagram());

        return organizationModel;
    }
}