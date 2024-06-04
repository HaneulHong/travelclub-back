package io.namoosori.travelclub.store.jpastroe.jpo;

import io.namoosori.travelclub.aggregate.club.TravelClub;
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
@Table(name="TRAVEL_CLUB")
public class ClubJpo {

    @Id
    private String id;  // pk
    private String name;
    private String intro;
    private long foundationTime;

    public ClubJpo(TravelClub travelClub) {
        BeanUtils.copyProperties(travelClub, this);
    }

    public TravelClub toDomain() {
        TravelClub travelClub = new TravelClub(this.name, this.intro);
        travelClub.setId(this.id);
        travelClub.setFoundationTime(this.foundationTime);

        return travelClub;
    }
}
