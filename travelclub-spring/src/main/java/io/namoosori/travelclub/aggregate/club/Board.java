package io.namoosori.travelclub.aggregate.club;

import io.namoosori.travelclub.aggregate.Entity;
import io.namoosori.travelclub.shared.NameValue;
import io.namoosori.travelclub.shared.NameValueList;
import io.namoosori.travelclub.util.helper.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Board extends Entity {
    private int sequence;
    private String name;
    private String adminEmail;
    private String createDate;

    private String clubName;
    public Board(String id) {
        super(id);
        this.sequence = 1;
    }

    public Board(String name, String adminEmail) {
        super();
        this.name = name;
        this.adminEmail = adminEmail;
        this.createDate = DateUtil.today();
    }

    public Board(TravelClub travelClub, String name, String adminEmail) {
        super();
        this.clubName = travelClub.getName();
        this.name = name;
        this.adminEmail = adminEmail;
        this.createDate = DateUtil.today();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Board Id : ").append(id);
        sb.append(", name : ").append(name);
        sb.append(", adminEmail : ").append(adminEmail);
        sb.append(", createDate : ").append(createDate);

        return sb.toString();
    }

    public void modifyValues(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "name":
                    this.name = value;
                    break;
                case "intro":
                    this.adminEmail = value;
                    break;
            }
        }
    }
}
