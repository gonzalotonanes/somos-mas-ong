package com.alkemy.ong.web.controller;

import com.alkemy.ong.domain.activities.ActivityModel;
import com.alkemy.ong.domain.activities.ActivityService;
import com.alkemy.ong.web.dto.ActivityDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/activities")
public class ActivityController {

    private ActivityService activityService;

    public ActivityController(ActivityService service) {
        this.activityService = service;
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping()
    public ResponseEntity<?> createActivity(@Valid @RequestBody ActivityDto activityDto) {

        ActivityModel activityModel = activityDto.toModel();
        activityModel = this.activityService.createActivity(activityModel);

        return new ResponseEntity<>(activityDto.toDto(activityModel), HttpStatus.CREATED);
    }

    @ApiOperation(value = "", authorizations = { @Authorization(value="jwtToken") })
    @PreAuthorize("hasAuthority('Admin')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateActivity(@PathVariable("id") Long id, @Valid @RequestBody ActivityDto activityDto) {

        activityDto.setId(id);
        ActivityModel activityModel = activityService.updateActivity(activityDto.toModel());
        return new ResponseEntity<>(activityDto.toDto(activityModel), HttpStatus.OK);
    }
}
