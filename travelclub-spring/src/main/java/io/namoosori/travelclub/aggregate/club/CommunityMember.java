package io.namoosori.travelclub.aggregate.club;

import com.google.gson.Gson;
import io.namoosori.travelclub.aggregate.Entity;
import io.namoosori.travelclub.aggregate.club.vo.Address;
import io.namoosori.travelclub.shared.NameValue;
import io.namoosori.travelclub.shared.NameValueList;
import io.namoosori.travelclub.util.exception.InvalidEmailException;
import io.namoosori.travelclub.util.exception.InvalidPhoneNumberException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CommunityMember {
	private String email;
	private String password;
	private String name;
	private String phoneNumber;
	private String nickName;
	private String birthDay;

	//private List<Address> addresses;

	/*public CommunityMember() {
		super();
		this.addresses = new ArrayList<>();
	}*/


	public CommunityMember(String email, String password, String name, String nickName, String phoneNumber, String birthDay) {
		super();
		this.email = checkEmailValidation(email);
		this.password = password;
		this.name = name;
		this.nickName = nickName;
		this.phoneNumber = checkPhoneNumberValidation(phoneNumber);
		this.birthDay = birthDay;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Name:").append(name);
		builder.append(", email:").append(email);
		builder.append(", nickname:").append(nickName);
		builder.append(", phone number:").append(phoneNumber);
		builder.append(", birthDay:").append(birthDay);

		/*if (addresses != null) {
			int i = 1;
			for (Address address : addresses) {
				builder.append(", Address[").append(i).append("]").append(address.toString());
			}
		}*/

		return builder.toString();
	}

	public void checkValidation() {
		checkEmailValidation(email);
	}

    public String checkEmailValidation(String email) {
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
		java.util.regex.Matcher m = p.matcher(email);
		boolean valid = m.matches();

		if (!valid) {
			throw new InvalidEmailException("Email is invalid.");
		}

		return email;
	}

	public String checkPhoneNumberValidation(String phoneNumber) {
		if (phoneNumber == null) {
			throw new InvalidPhoneNumberException("Phone number is null.");
		}
		String pPattern = "^(010-?\\d{4}-?\\d{4})$";
		java.util.regex.Pattern p = java.util.regex.Pattern.compile(pPattern);
		java.util.regex.Matcher m = p.matcher(phoneNumber);
		boolean valid = m.matches();

		if (!valid) {
			throw new InvalidPhoneNumberException("Phone number is invalid.");
		}

		return phoneNumber;
	}

	public void modifyValues(NameValueList nameValues) {
		for (NameValue nameValue : nameValues.getNameValues()) {
			String value = nameValue.getValue();
			switch (nameValue.getName()) {
				case "password":
					this.password = value;
					break;
				case "name":
					this.name = value;
					break;
				case "phoneNumber":
					this.phoneNumber = value;
					break;
				case "nickName":
					this.nickName = value;
					break;
				case "birthDay":
					this.birthDay = value;
					break;
			}
		}
	}

	public static CommunityMember sample() {
		CommunityMember member = new CommunityMember("mymy@nextree.co.kr", "1234", "Minsoo Lee", "MING", "01033211001", "990204");
		//member.getAddresses().add(Address.sampleHomeAddress());
		return member;
	}

	public static void main(String[] args) {
		System.out.println(new Gson().toJson(sample()));
	}
}
