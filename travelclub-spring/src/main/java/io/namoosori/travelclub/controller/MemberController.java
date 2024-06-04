package io.namoosori.travelclub.controller;

import io.namoosori.travelclub.aggregate.club.CommunityMember;
import io.namoosori.travelclub.service.MemberService;
import io.namoosori.travelclub.service.sdo.LoginCdo;
import io.namoosori.travelclub.service.sdo.MemberCdo;
import io.namoosori.travelclub.shared.NameValueList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public String register(@RequestBody MemberCdo memberCdo) {

        return memberService.registerMember(memberCdo);
    }

    @PostMapping("/login")
    public CommunityMember login(@RequestBody LoginCdo loginCdo) {
        return memberService.login(loginCdo);
    }

    @GetMapping ("/{memberEmail}")
    public CommunityMember findByEmail(@PathVariable String memberEmail) {
        return memberService.findMemberByEmail(memberEmail);
    }

    /*@GetMapping("/{memberId}")
    public CommunityMember findById(@PathVariable String memberId) {
        return memberService.findMemberById(memberId);
    }*/

    @GetMapping("/list/{name}")
    public List<CommunityMember> findByName(@PathVariable String name) {
        return memberService.findMembersByName(name);
    }

    @PutMapping("/{memberEmail}")
    public void modify(@PathVariable String memberEmail, @RequestBody NameValueList nameValueList) {
        memberService.modifyMember(memberEmail, nameValueList);
    }

    @DeleteMapping("/{memberEmail}")
    public void delete(@PathVariable String memberEmail) {
        memberService.removeMember(memberEmail);
    }
}
