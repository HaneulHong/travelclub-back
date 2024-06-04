package io.namoosori.travelclub.service.logic;

import io.namoosori.travelclub.aggregate.club.CommunityMember;
import io.namoosori.travelclub.service.MemberService;
import io.namoosori.travelclub.service.sdo.LoginCdo;
import io.namoosori.travelclub.service.sdo.MemberCdo;
import io.namoosori.travelclub.shared.NameValueList;
import io.namoosori.travelclub.store.MemberStore;
import io.namoosori.travelclub.util.exception.MemberDuplicationException;
import io.namoosori.travelclub.util.exception.NoSuchMemberException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceLogic implements MemberService {
	private MemberStore memberStore;

	public MemberServiceLogic(MemberStore memberStore) {
		this.memberStore = memberStore;
	}

	@Override
	public String registerMember(MemberCdo newMemberCdo) {
		String email = newMemberCdo.getEmail();
		CommunityMember foundedmember = memberStore.retrieveByEmail(email);

		if (foundedmember != null) {
			throw new MemberDuplicationException("Member already exists with email: " + foundedmember.getEmail());
		}

		foundedmember = new CommunityMember(
				newMemberCdo.getEmail(),
				newMemberCdo.getPassword(),
				newMemberCdo.getName(),
				newMemberCdo.getNickName(),
				newMemberCdo.getPhoneNumber(),
				newMemberCdo.getBirthDay()
		);
		foundedmember.setNickName(newMemberCdo.getNickName());
		foundedmember.setBirthDay(newMemberCdo.getBirthDay());

		foundedmember.checkValidation();

		memberStore.create(foundedmember);

		return foundedmember.getEmail();
	}

	@Override
	public void emailDuplication(String email) {
		CommunityMember member = new CommunityMember();
		member.checkEmailValidation(email);
		if (memberStore.retrieveByEmail(email) != null) {
			throw new MemberDuplicationException("Member already exist with email " + email);
		}
	}

	@Override
	public CommunityMember login(LoginCdo loginCdo) {
		CommunityMember member = new CommunityMember();
		member.checkEmailValidation(loginCdo.getEmail());
		member = findMemberByEmail(loginCdo.getEmail());
		if (!member.getPassword().equals(loginCdo.getPassword())) {
			throw new IllegalArgumentException("Wrong Password");
		}
		return member;
	}

	@Override
	public CommunityMember findMemberByEmail(String email) {
		return memberStore.retrieveByEmail(email);
	}

	@Override
	public List<CommunityMember> findMembersByName(String name) {
		return memberStore.retrieveByName(name);
	}

	@Override
	public void modifyMember(String email, NameValueList nameValueList) {
		CommunityMember targetMember = memberStore.retrieveByEmail(email);

		if (targetMember == null) {
			throw new NoSuchMemberException("No such member with id " + email);
		}

		targetMember.modifyValues(nameValueList);

		memberStore.update(targetMember);
	}

	@Override
	public void removeMember(String email) {
		if (!memberStore.exists(email)) {
			throw new NoSuchMemberException("No such member with id " + email);
		}

		memberStore.delete(email);
	}
}
