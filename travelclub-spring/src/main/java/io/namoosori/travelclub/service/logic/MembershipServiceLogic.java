package io.namoosori.travelclub.service.logic;

import io.namoosori.travelclub.aggregate.club.CommunityMember;
import io.namoosori.travelclub.aggregate.club.Membership;
import io.namoosori.travelclub.aggregate.club.TravelClub;
import io.namoosori.travelclub.service.sdo.MembershipCdo;
import io.namoosori.travelclub.aggregate.club.vo.RoleInClub;
import io.namoosori.travelclub.service.MembershipService;
import io.namoosori.travelclub.shared.NameValueList;
import io.namoosori.travelclub.store.ClubStore;
import io.namoosori.travelclub.store.MemberStore;
import io.namoosori.travelclub.store.MembershipStore;
import io.namoosori.travelclub.util.exception.MembershipDuplicationException;
import io.namoosori.travelclub.util.exception.NoSuchClubException;
import io.namoosori.travelclub.util.exception.NoSuchMemberException;
import io.namoosori.travelclub.util.exception.NoSuchMembershipException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MembershipServiceLogic implements MembershipService {
    private MembershipStore membershipStore;
    private ClubStore clubStore;
    private MemberStore memberStore;

    public MembershipServiceLogic(MembershipStore membershipStore, ClubStore clubStore, MemberStore memberStore) {
        this.membershipStore = membershipStore;
        this.memberStore = memberStore;
        this.clubStore = clubStore;
    }

    @Override
    public String registerMembership(MembershipCdo membershipCdo) {
        String clubId = membershipCdo.getClubId();
        String memberEmail = membershipCdo.getMemberEmail();
        RoleInClub role = membershipCdo.getRole();

        TravelClub club = clubStore.retrieve(clubId);

        if (club == null) {
            throw new NoSuchClubException("No such club with id " + clubId);
        }

        CommunityMember member = memberStore.retrieveByEmail(memberEmail);

        if (member == null) {
            throw new NoSuchMemberException("No such member with id " + memberEmail);
        }

        Membership membership = findMembershipByClubIdAndMemberEmail(clubId, memberEmail);

        if (membership != null) {
            throw new MembershipDuplicationException("Member already exists in the club");
        }

        membership = new Membership(clubId, memberEmail);
        membership.setRole(role);

        String membershipId = membershipStore.create(membership);

        return membershipId;
    }

    @Override
    public Membership findMembership(String membershipId) {
        return membershipStore.retrieve(membershipId);
    }

    /*@Override
    public Membership findMembershipByClubIdAndMemberId(String clubId, String memberEmail) {
        return membershipStore.retrieve(clubId);
    }*/

    @Override
    public Membership findMembershipByClubIdAndMemberEmail(String clubId, String memberEmail) {
        CommunityMember member = memberStore.retrieveByEmail(memberEmail);

        if (member == null) {
            throw new NoSuchMemberException("No such member with email " + memberEmail);
        }

        return membershipStore.retrieve(clubId);
    }

    @Override
    public List<Membership> findAllMembershipsOfClub(String clubId) {
        return membershipStore.retrieveByClubId(clubId);
    }

    @Override
    public List<Membership> findAllMembershipsOfMember(String memberEmail) {
        return membershipStore.retrieveByMemberEmail(memberEmail);
    }

    @Override
    public void modifyMembership(String membershipId, NameValueList nameValueList) {
        Membership membership = membershipStore.retrieve(membershipId);

        if (membership == null) {
            throw new NoSuchMembershipException("No such membership");
        }

        membership.modifyValues(nameValueList);

        membershipStore.update(membership);
    }

    @Override
    public void removeMembership(String membershipId) {
        membershipStore.delete(membershipId);
    }
}
