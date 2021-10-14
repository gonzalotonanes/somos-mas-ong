package com.alkemy.ong.domain.testimonials;

import com.alkemy.ong.domain.utils.Page;

public interface TestimonialService {

    TestimonialModel save(TestimonialModel testimonialModel);

    TestimonialModel update(TestimonialModel testimonialModel);

    void deleteById(Long testimonialId);

    Page<TestimonialModel> findAll(int page);

}
