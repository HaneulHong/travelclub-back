package io.namoosori.travelclub.controller;

import io.namoosori.travelclub.aggregate.club.Membership;
import io.namoosori.travelclub.service.MembershipService;
import io.namoosori.travelclub.service.sdo.MembershipCdo;
import io.namoosori.travelclub.shared.NameValueList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membership")
public class MembershipController {
    private MembershipService membershipService;

    public MembershipController(MembershipService membershipService) {
        this.membershipService = membershipService;
    }

    @PostMapping
    public String register(@RequestBody MembershipCdo membershipCdo) {
        return membershipService.registerMembership(membershipCdo);
    }

    @GetMapping("/{membershipId}")
    public Membership find(@PathVariable String membershipId) {
        return membershipService.findMembership(membershipId);
    }

    /*@GetMapping("/club/{clubId}/member/{memberId}")
    public Membership findMembershipByClubIdAndMemberId(@PathVariable String clubId, @PathVariable String memberId) {
        System.out.println(clubId);
        System.out.println(memberId);
        return membershipService.findMembershipByClubIdAndMemberId(clubId, memberId);
    }*/

    @GetMapping("/club/{clubId}/email/{memberEmail}")
    public Membership findMembershipByClubIdAndMemberEmail(@PathVariable String clubId, @PathVariable String memberEmail) {
        System.out.println(clubId);
        System.out.println(memberEmail);
        return membershipService.findMembershipByClubIdAndMemberEmail(clubId, memberEmail);
    }

    @GetMapping("/club/{clubId}")
    public List<Membership> findAllMembershipsOfClub(@PathVariable String clubId) {
        return membershipService.findAllMembershipsOfClub(clubId);
    }

    @GetMapping("/member/{memberEmail}")
    public List<Membership> findAllMembershipsOfMember(@PathVariable String memberEmail) {
        return membershipService.findAllMembershipsOfMember(memberEmail);
    }

    @PutMapping("/{membershipId}")
    public void modify(@PathVariable String membershipId, @RequestBody NameValueList nameValueList) {
        membershipService.modifyMembership(membershipId, nameValueList);
    }

    @DeleteMapping("/{membershipId}")
    public void remove(@PathVariable String membershipId) {
        membershipService.removeMembership(membershipId);
    }
}
