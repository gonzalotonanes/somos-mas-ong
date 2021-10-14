package com.alkemy.ong.domain.sample;

import com.alkemy.ong.domain.exceptions.DomainException;
import com.alkemy.ong.domain.utils.Page;
import org.springframework.stereotype.Service;

@Service
public class DefaultSampleService implements SampleService {

    private final SampleRepository repository;

    public DefaultSampleService(SampleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Sample> findAll(int page) {
        return repository.findAll(page);
    }

    @Override
    public Sample create(Sample sample) {
        return repository.create(sample);
    }

    @Override
    public Sample update(Sample sample) {
        return repository.update(sample);
    }

    @Override
    public Sample findById(Long id) {
        return repository.findById(id).orElseThrow(DomainException::new);
    }
}
