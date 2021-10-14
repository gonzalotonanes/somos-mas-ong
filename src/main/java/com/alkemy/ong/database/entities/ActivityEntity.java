package com.alkemy.ong.database.entities;

import lombok.Data;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "activities")
@SQLDelete(sql = "UPDATE activities SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Data
public class ActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "content", length = 256, nullable = false)
    private String content;

    @Column(name = "image", length = 256, nullable = false)
    private String image;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(value = GenerationTime.INSERT)
    @Column(name = "created_at", updatable = false, nullable = false, insertable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP")
    private Date createAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Generated(value = GenerationTime.ALWAYS)
    @Column(name = "updated_at", updatable = false, nullable = false, insertable = false,
            columnDefinition = "datetime default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Date updateAt;

    private boolean deleted = Boolean.FALSE;

}
