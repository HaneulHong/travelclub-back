package io.namoosori.travelclub.service.sdo;

import io.namoosori.travelclub.aggregate.club.vo.RoleInClub;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipCdo implements Serializable {
    private String clubId;
    private String memberEmail;
    private RoleInClub role;
}
