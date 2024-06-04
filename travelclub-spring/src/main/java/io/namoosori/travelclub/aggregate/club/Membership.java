package io.namoosori.travelclub.aggregate.club;

import io.namoosori.travelclub.aggregate.Entity;
import io.namoosori.travelclub.aggregate.club.vo.RoleInClub;
import io.namoosori.travelclub.shared.NameValue;
import io.namoosori.travelclub.shared.NameValueList;
import io.namoosori.travelclub.util.helper.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Membership extends Entity {
	private String clubId;
	private String memberEmail;
	private RoleInClub role;
	private String joinDate;

	public Membership(String id) {
		super(id);
	}

	public Membership(String clubId, String memberEmail) {
		super();
		this.clubId = clubId;
		this.memberEmail = memberEmail;
		this.role = RoleInClub.Member;
		this.joinDate = DateUtil.today();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("club Id:").append(clubId);
		builder.append(", member Id:").append(memberEmail);
		builder.append(", role:").append(role.name());
		builder.append(", join date:").append(joinDate);

		return builder.toString();
	}

	public void modifyValues(NameValueList nameValueList) {
		for (NameValue nameValue : nameValueList.getNameValues()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "role":
					this.role = RoleInClub.valueOf(value);
					break;
			}
		}
	}

	public static Membership sample() {
		return new Membership(
				TravelClub.sample().getId(),
				CommunityMember.sample().getEmail()
		);
	}

	public static void main(String[] args) {
		System.out.println(sample().toString());
	}
}