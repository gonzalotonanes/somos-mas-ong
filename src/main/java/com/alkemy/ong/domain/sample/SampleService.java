package com.alkemy.ong.domain.sample;

import com.alkemy.ong.domain.utils.Page;

public interface SampleService {
    Page<Sample> findAll(int page);

    Sample create(Sample sample);

    Sample update(Sample sample);

    Sample findById(Long id);
}
