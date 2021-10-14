package com.alkemy.ong.domain.testimonials;

import com.alkemy.ong.domain.utils.Page;

public interface TestimonialRepo {

    TestimonialModel create(TestimonialModel testimonialModel);

    TestimonialModel updateTestimonial(TestimonialModel testimonialModel);

    void deleteByTestimonialId(Long testimonialId);

    Page<TestimonialModel> findAll(int page);
}
