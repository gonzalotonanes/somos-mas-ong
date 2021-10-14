package com.alkemy.ong.database.jparepositories;


import com.alkemy.ong.database.entities.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberJPARepository extends JpaRepository<MemberEntity, Long> {

}
