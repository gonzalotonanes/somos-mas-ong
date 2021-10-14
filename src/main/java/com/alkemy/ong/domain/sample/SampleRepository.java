package com.alkemy.ong.domain.sample;

import com.alkemy.ong.domain.utils.Page;

import java.util.Optional;

public interface SampleRepository {
    Page<Sample> findAll(int page);

    Sample create(Sample sample);

    Sample update(Sample sample);

    Optional<Sample> findById(Long id);
}
