package io.namoosori.travelclub.store.jpastroe;

import io.namoosori.travelclub.aggregate.club.CommunityMember;
import io.namoosori.travelclub.store.MemberStore;
import io.namoosori.travelclub.store.jpastroe.jpo.MemberJpo;
import io.namoosori.travelclub.store.jpastroe.repository.MemberRepository;
import io.namoosori.travelclub.util.exception.NoSuchMemberException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemberJpaStore implements MemberStore {

    private MemberRepository memberRepository;

    public MemberJpaStore(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public String create(CommunityMember member) {
        memberRepository.save(new MemberJpo(member));
        return member.getEmail();
    }

    /*@Override
    public CommunityMember retrieve(String memberId) {
        Optional<MemberJpo> memberJpo = memberRepository.findById(memberId);
        return memberJpo.map(MemberJpo::toDomain).orElse(null);
    }*/

    @Override
    public CommunityMember retrieveByEmail(String email) {
        Optional<MemberJpo> memberJpo = memberRepository.findByEmail(email);
        return memberJpo.map(MemberJpo::toDomain).orElse(null);
    }

    @Override
    public List<CommunityMember> retrieveByName(String name) {
        List<MemberJpo> memberJpos = memberRepository.findByName(name);
        return memberJpos.stream().map(MemberJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(CommunityMember member) {
        memberRepository.save(new MemberJpo(member));
    }

    @Override
    public void delete(String email) {
        memberRepository.deleteByEmail(email);
    }

    @Override
    public boolean exists(String memberId) {
        return memberRepository.existsById(memberId);
    }
}
