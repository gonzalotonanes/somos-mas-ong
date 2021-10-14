package com.alkemy.ong.domain.members;

import com.alkemy.ong.domain.utils.Page;

public interface MemberService {

    Page<MemberModel> getMembers(int page);
    MemberModel createMember(MemberModel member);
    MemberModel deleteMember(Long id);
    MemberModel updateMember(MemberModel member);
}
