package com.alkemy.ong.domain.slides;

import java.util.List;

public interface SlideService {
    List<SlideModel> getAll();
    SlideModel getSlide(int id);
    SlideModel update(SlideModel slide);
    void delete(int id);
    List<SlideModel> getAllByOrgIdOrdered(int orgId);
    SlideModel create(SlideModel slide);
}
