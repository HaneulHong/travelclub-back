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
public class BoardCdo implements Serializable {
    private String name;
    private String adminEmail;
}
