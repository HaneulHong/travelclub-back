package io.namoosori.travelclub.store.jpastroe.repository;

import io.namoosori.travelclub.aggregate.club.Membership;
import io.namoosori.travelclub.store.jpastroe.jpo.MembershipJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MembershipRepository extends JpaRepository<MembershipJpo, String> {
    MembershipJpo findByClubIdAndMemberEmail(String clubId, String memberEmail);
    List<MembershipJpo> findByClubId(String clubId);
    List<MembershipJpo> findByMemberEmail(String memberEmail);
}
