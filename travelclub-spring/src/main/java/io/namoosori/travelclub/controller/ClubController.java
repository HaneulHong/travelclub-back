package io.namoosori.travelclub.controller;


import io.namoosori.travelclub.aggregate.club.TravelClub;
import io.namoosori.travelclub.service.ClubService;
import io.namoosori.travelclub.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.shared.NameValueList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club")
public class ClubController {

    private ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping  // localhost:8080/club
    public String register(@RequestBody TravelClubCdo clubCdo) {

        return clubService.registerClub(clubCdo);
    }

    @GetMapping("all")
    public List<TravelClub> findAll() {
        return clubService.findAll();
    }

    @GetMapping("/{clubId}")
    public TravelClub find(@PathVariable String clubId) {
        return clubService.findClubById(clubId);
    }

    @GetMapping   // localhost:8080/club?name=클럽이름
    public List<TravelClub> findByName(@RequestParam String name) {
        return clubService.findClubsByName(name);
    }

    @PutMapping("/{clubId}")
    public void modify(@PathVariable String clubId, @RequestBody NameValueList nameValueList) {
        clubService.modify(clubId, nameValueList);
    }

    @DeleteMapping("/{clubId}")
    public void delete(@PathVariable String clubId) {
        clubService.remove(clubId);
    }
}
