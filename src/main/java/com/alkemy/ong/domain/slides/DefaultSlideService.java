package com.alkemy.ong.domain.slides;

import com.alkemy.ong.domain.exceptions.DomainException;
import com.alkemy.ong.objectstorage.ImageStorage;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultSlideService implements SlideService {
    private SlideRepository repository;
    private ImageStorage imageStorage;

    public DefaultSlideService(SlideRepository repository, ImageStorage imageStorage) {
        this.imageStorage = imageStorage;
        this.repository = repository;
    }

    @Override
    public List<SlideModel> getAll() {
        return repository.getAll();
    }

    @Override
    public SlideModel getSlide(int id) {
        return repository.getById(id).orElseThrow(DomainException::new);
    }

    @Override
    public SlideModel update(SlideModel slide) {
        final SlideModel slideModel = repository.getById(slide.getId()).orElseThrow(DomainException::new);
        slideModel.setIdOrganization(slide.getIdOrganization());
        slideModel.setOrder(slide.getOrder());
        slideModel.setImageUrl(slide.getImageUrl());
        slideModel.setText(slide.getText());
        slideModel.setDeleted(slide.isDeleted());
        slideModel.setUpdatedAt(LocalDateTime.now());
        return repository.update(slideModel);
    }

    @Override
    public void delete(int id) {
        repository.delete(getSlide(id));
    }

    @Override
    public List<SlideModel> getAllByOrgIdOrdered(int orgId) {
        final List<SlideModel> slideModels = repository.getAllByOrgId(orgId);
        return slideModels
                .stream()
                .sorted(Comparator.comparingInt(SlideModel::getIdOrganization))
                .collect(Collectors.toList());
    }

    @Override
    public SlideModel create(SlideModel slide) {
        slide = resolveOrder(slide);
        final String bucketUrl = imageStorage.upload(getImageAsFile(slide));
        slide.setImageUrl(bucketUrl);
        return repository.create(slide);
    }

    private MultipartFile getImageAsFile(SlideModel slide) {
        final byte[] bytes = Base64.decodeBase64(slide.getImageUrl());
        return new Base64Decoded(bytes, "slide_" + slide.getOrder() + "org_" + slide.getIdOrganization());
    }

    private SlideModel resolveOrder(SlideModel slide) {
        if (slide.getOrder() == 0) {
            slide.setOrder(repository.getNextOrdinalNumber(slide.getIdOrganization()));
        }
        return slide;
    }
}