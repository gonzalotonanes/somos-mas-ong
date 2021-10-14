package com.alkemy.ong.web.dto;

import com.alkemy.ong.domain.activities.ActivityModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ActivityDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    @Size(min = 3, max = 50, message = "Field name requires at least 3 characters")
    @NotBlank(message = "Field name can't be empty")
    private String name;
    @NotBlank(message = "Field content can't be empty")
    @Size(max = 256, message = "Field content requires a maximum of 256 characters")
    private String content;
    @NotBlank(message = "Field image can't be empty")
    @Size(max = 256, message = "Field img requires a maximum of 256 characters")
    private String image;


    public ActivityDto(ActivityModel model) {
        this.content = model.getContent();
        this.name = model.getName();
        this.image = model.getImage();
    }

    public ActivityModel toModel() {
        ActivityModel activityModel = new ActivityModel();
        activityModel.setId(this.getId());
        activityModel.setName(this.getName());
        activityModel.setContent(this.getContent());
        activityModel.setImage(this.getImage());

        return activityModel;
    }

    public ActivityDto toDto(ActivityModel activityModel) {
        this.setId(activityModel.getId());
        this.setName(activityModel.getName());
        this.setImage(activityModel.getImage());
        this.setContent(activityModel.getContent());

        return this;
    }
}
