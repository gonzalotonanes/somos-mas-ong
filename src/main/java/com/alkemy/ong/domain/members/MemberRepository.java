package com.alkemy.ong.domain.members;

import com.alkemy.ong.database.entities.MemberEntity;

import com.alkemy.ong.domain.utils.Page;

import java.util.Optional;

public interface MemberRepository {

    Page<MemberModel> getMembers(int page);
    MemberModel createMember(MemberModel member);
    void deleteMember(Long id);
    MemberModel updateMember(MemberModel member);
    Optional<MemberEntity> findById(Long id);

}
