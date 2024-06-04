package io.namoosori.travelclub.store.jpastroe.jpo;

import io.namoosori.travelclub.aggregate.club.CommunityMember;
import io.namoosori.travelclub.aggregate.club.vo.Address;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "CommunityMember")
public class MemberJpo {
    @Id
    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private String nickName;
    private String birthDay;

    //private List<Address> addresses;

    public MemberJpo(CommunityMember communityMember) {
        BeanUtils.copyProperties(communityMember, this);
        //this.addresses = communityMember.getAddresses();
    }

    public CommunityMember toDomain() {
        CommunityMember communityMember = new CommunityMember(this.email, this.password, this.name, this.nickName , this.phoneNumber, this.birthDay);
        //communityMember.setAddresses(this.getAddresses());

        return communityMember;
    }
}
