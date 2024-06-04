package io.namoosori.travelclub.service;

import io.namoosori.travelclub.aggregate.club.CommunityMember;
import io.namoosori.travelclub.service.sdo.LoginCdo;
import io.namoosori.travelclub.service.sdo.MemberCdo;
import io.namoosori.travelclub.shared.NameValueList;

import java.util.List;

public interface MemberService {
	String registerMember(MemberCdo member);
	void emailDuplication(String memberEmail);
	CommunityMember login(LoginCdo loginCdo);
	CommunityMember findMemberByEmail(String memberEmail);
	List<CommunityMember> findMembersByName(String name);
	void modifyMember(String memberEmail, NameValueList member);
	void removeMember(String memberEmail);
}