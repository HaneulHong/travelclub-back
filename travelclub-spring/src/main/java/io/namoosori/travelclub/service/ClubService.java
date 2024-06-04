package io.namoosori.travelclub.service;

import io.namoosori.travelclub.aggregate.club.TravelClub;
import io.namoosori.travelclub.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.shared.NameValueList;

import java.util.List;

public interface ClubService {
	String registerClub(TravelClubCdo club);
	TravelClub findClubById(String id);
	List<TravelClub> findClubsByName(String name);
	List<TravelClub> findAll();
	void modify(String clubId, NameValueList nameValues);
	void remove(String clubId);
}
