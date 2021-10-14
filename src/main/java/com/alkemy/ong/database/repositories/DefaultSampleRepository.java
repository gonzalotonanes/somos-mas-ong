package com.alkemy.ong.database.repositories;

import com.alkemy.ong.database.entities.SampleEntity;
import com.alkemy.ong.database.jparepositories.SampleJpaRepository;
import com.alkemy.ong.domain.sample.Sample;
import com.alkemy.ong.domain.sample.SampleRepository;
import com.alkemy.ong.domain.utils.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.alkemy.ong.commons.PageUtils.DEFAULT_PAGE_SIZE;
import static java.util.stream.Collectors.toList;

@Repository
public class DefaultSampleRepository implements SampleRepository {

    private final SampleJpaRepository jpaRepository;

    public DefaultSampleRepository(SampleJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Page<Sample> findAll(int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        org.springframework.data.domain.Page<SampleEntity> samples = jpaRepository.findAll(pageable);

        return toPage(samples);
    }

    private Page<Sample> toPage(org.springframework.data.domain.Page<SampleEntity> samples) {
        Page<Sample> page = new Page<>();
        page.setContent(samples.getContent().stream()
                .map(this::toModel).collect(toList()));
        page.setHasNextPage(samples.hasNext());
        page.setHasPreviousPage(samples.hasPrevious());
        page.setCurrentPage(samples.getPageable().getPageNumber());
        return page;
    }

    @Override
    public Sample create(Sample sample) {
        return toModel(jpaRepository.save(toEntity(sample)));
    }

    @Override
    public Sample update(Sample sample) {
        return null;
    }

    @Override
    public Optional<Sample> findById(Long id) {
        return jpaRepository.findById(id)
                .map(this::toModel);
    }

    private SampleEntity toEntity(Sample sample) {
        SampleEntity sampleEntity = new SampleEntity();
        sampleEntity.setName(sample.getName());
        sampleEntity.setDescription(sample.getDescription());
        return sampleEntity;
    }

    private Sample toModel(SampleEntity entity) {
        Sample sample = new Sample();
        sample.setId(entity.getId());
        sample.setName(entity.getName());
        sample.setDescription(entity.getDescription());
        return sample;
    }
}
