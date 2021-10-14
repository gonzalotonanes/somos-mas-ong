package com.alkemy.ong.database.repositories;

import com.alkemy.ong.database.entities.TestimonialEntity;
import com.alkemy.ong.database.jparepositories.TestimonialJpaRepository;
import com.alkemy.ong.domain.testimonials.TestimonialModel;
import com.alkemy.ong.domain.testimonials.TestimonialRepo;
import com.alkemy.ong.domain.utils.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import static com.alkemy.ong.commons.PageUtils.DEFAULT_PAGE_SIZE;
import static java.util.stream.Collectors.toList;

@Repository
public class DefaultTestimonialRepository implements TestimonialRepo {

    private TestimonialJpaRepository testimonialJpaRepository;

    public DefaultTestimonialRepository(TestimonialJpaRepository testimonialJpaRepository){
        this.testimonialJpaRepository = testimonialJpaRepository;
    }

    public TestimonialModel convertToModel(TestimonialEntity testimonialEntity){

        TestimonialModel testimonialModel = new TestimonialModel();

        testimonialModel.setId(testimonialEntity.getId());
        testimonialModel.setName(testimonialEntity.getName());
        testimonialModel.setImage(testimonialEntity.getImage());
        testimonialModel.setContent(testimonialEntity.getContent());
        testimonialModel.setDeleted(testimonialEntity.getDeleted());
        testimonialModel.setCreatedAt(testimonialEntity.getCreatedAt());
        testimonialModel.setUpdatedAt(testimonialEntity.getUpdatedAt());
        return testimonialModel;
    }

    @Override
    public TestimonialModel create(TestimonialModel testimonialModel){

        TestimonialEntity testimonialEntity = new TestimonialEntity();

        testimonialEntity.setName(testimonialModel.getName());
        testimonialEntity.setImage(testimonialModel.getImage());
        testimonialEntity.setContent(testimonialModel.getContent());
        testimonialEntity.setDeleted(testimonialModel.getDeleted());
        testimonialEntity.setCreatedAt(testimonialModel.getCreatedAt());
        testimonialEntity.setUpdatedAt(testimonialModel.getUpdatedAt());

        testimonialEntity = testimonialJpaRepository.save(testimonialEntity);

        return testimonialModel;
    }

    @Override
    public TestimonialModel updateTestimonial(TestimonialModel testimonialModel) {

        Optional<TestimonialEntity> testimonial = testimonialJpaRepository.findById(testimonialModel.getId());

        testimonial.get().setName(testimonialModel.getName());
        testimonial.get().setImage(testimonialModel.getImage());
        testimonial.get().setContent(testimonialModel.getContent());
        testimonial.get().setDeleted(testimonialModel.getDeleted());
        testimonial.get().setCreatedAt(testimonialModel.getCreatedAt());
        testimonial.get().setUpdatedAt(testimonialModel.getUpdatedAt());

        testimonialJpaRepository.save(testimonial.get());

        testimonialModel = convertToModel(testimonial.get());

        return testimonialModel;
    }

    @Override
    public void deleteByTestimonialId(Long testimonialId) {
        testimonialJpaRepository.deleteById(testimonialId);
    }

    @Override
    public Page<TestimonialModel> findAll(int page) {
        Pageable pageable = PageRequest.of(page, DEFAULT_PAGE_SIZE);
        org.springframework.data.domain.Page<TestimonialEntity> testimonials = testimonialJpaRepository.findAll(pageable);
        return toPage(testimonials);
    }

    private Page<TestimonialModel> toPage(org.springframework.data.domain.Page<TestimonialEntity> testimonials){
        Page<TestimonialModel> page = new Page<>();
        page.setContent(testimonials.getContent().stream().map(this::convertToModel).collect(toList()));
        page.setHasNextPage(testimonials.hasNext());
        page.setHasPreviousPage(testimonials.hasPrevious());
        page.setCurrentPage(testimonials.getPageable().getPageNumber());
        return page;
    }
}
