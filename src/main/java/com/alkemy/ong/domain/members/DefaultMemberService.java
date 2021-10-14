package com.alkemy.ong.domain.members;

import com.alkemy.ong.database.entities.MemberEntity;
import com.alkemy.ong.domain.utils.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public
class DefaultMemberService implements MemberService {

    private MemberRepository memberRepository;

    public DefaultMemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Page<MemberModel> getMembers(int page) {
        return memberRepository.getMembers(page);
    }

    public MemberModel createMember(MemberModel member) {
        return memberRepository.createMember(member);
    }

    public MemberModel deleteMember(Long id){
        Optional<MemberEntity> member = memberRepository.findById(id);
        memberRepository.deleteMember(id);
        return toModel(member.orElseThrow(MemberDomainException::new));

    }


    private MemberModel toModel(MemberEntity mm) {
        MemberModel member = new MemberModel();
        member.setId(mm.getId());
        member.setName(mm.getName());
        member.setCreatedAt(mm.getCreatedAt());
        member.setDeleted(mm.isDeleted());
        member.setDescription(mm.getDescription());
        member.setFacebookUrl(mm.getFacebookUrl());
        member.setImage(mm.getImage());
        member.setInstagramUrl(mm.getInstagramUrl());
        member.setLinkedinUrl(mm.getLinkedinUrl());
        member.setUpdatedAt(mm.getUpdatedAt());
        return member;
    }

    public MemberModel updateMember(MemberModel member) {
        Optional<MemberEntity> result = memberRepository.findById(member.getId());
        memberRepository.updateMember(member);
        return toModel(result.orElseThrow(MemberDomainException::new));
    }


}
