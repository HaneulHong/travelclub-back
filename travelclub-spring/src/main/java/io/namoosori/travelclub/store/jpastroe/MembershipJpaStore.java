package io.namoosori.travelclub.store.jpastroe;

import io.namoosori.travelclub.aggregate.club.Membership;
import io.namoosori.travelclub.store.MembershipStore;
import io.namoosori.travelclub.store.jpastroe.jpo.MemberJpo;
import io.namoosori.travelclub.store.jpastroe.jpo.MembershipJpo;
import io.namoosori.travelclub.store.jpastroe.repository.MembershipRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MembershipJpaStore implements MembershipStore {

    private MembershipRepository membershipRepository;

    public MembershipJpaStore(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    @Override
    public String create(Membership membership) {
        membershipRepository.save(new MembershipJpo(membership));

        return membership.getId();
    }

    @Override
    public Membership retrieve(String membershipId) {
        Optional<MembershipJpo> membershipJpo = membershipRepository.findById(membershipId);

        return membershipJpo.stream().map(MembershipJpo::toDomain).findAny().orElse(null);
    }

    @Override
    public Membership retrieveByClubIdAndMemberEmail(String clubId, String memberEmail) {
        Optional<MembershipJpo> membershipJpo = Optional.ofNullable(membershipRepository.findByClubIdAndMemberEmail(clubId, memberEmail));

        return membershipJpo.map(MembershipJpo::toDomain).orElse(null);
    }

    @Override
    public List<Membership> retrieveByClubId(String clubId) {
        List<MembershipJpo> membershipJpos = membershipRepository.findByClubId(clubId);

        return membershipJpos.stream().map(MembershipJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Membership> retrieveByMemberEmail(String memberEmail) {
        List<MembershipJpo> membershipJpos = membershipRepository.findByMemberEmail(memberEmail);

        return membershipJpos.stream().map(MembershipJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(Membership membership) {
        membershipRepository.save(new MembershipJpo(membership));
    }

    @Override
    public void delete(String membershipId) {
        membershipRepository.deleteById(membershipId);
    }

    @Override
    public boolean exists(String membershipId) {
        return membershipRepository.existsById(membershipId);
    }
}
