package io.namoosori.travelclub.service.sdo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCdo implements Serializable {
    private String email;
    private String password;
    private String name;
    private String nickName;
    private String phoneNumber;
    private String birthDay;
}
