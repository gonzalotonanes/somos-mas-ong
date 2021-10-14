package com.alkemy.ong.domain.slides;

import java.util.List;
import java.util.Optional;

public interface SlideRepository {
    List<SlideModel> getAll();
    Optional<SlideModel> getById(int id);
    SlideModel update(SlideModel slide);
    void delete(SlideModel id);
    List<SlideModel> getAllByOrgId(int orgId);
    int getNextOrdinalNumber(int org);
    SlideModel create(SlideModel slide);
}
