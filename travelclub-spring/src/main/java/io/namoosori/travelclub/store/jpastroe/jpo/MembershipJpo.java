package io.namoosori.travelclub.store.jpastroe.jpo;

import io.namoosori.travelclub.aggregate.club.Membership;
import io.namoosori.travelclub.aggregate.club.vo.RoleInClub;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Membership")
public class MembershipJpo {
    @Id
    private String id;
    private String clubId;
    private String memberEmail;
    private RoleInClub role;
    private String joinDate;

    public MembershipJpo(Membership membership) {
        BeanUtils.copyProperties(membership, this);
    }

    public Membership toDomain() {
        Membership membership = new Membership(this.clubId, this.memberEmail);
        membership.setId(this.id);
        membership.setRole(this.role);
        membership.setJoinDate(this.joinDate);

        return membership;
    }
 }
