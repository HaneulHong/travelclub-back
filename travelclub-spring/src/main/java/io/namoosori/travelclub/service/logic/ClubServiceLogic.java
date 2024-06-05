package io.namoosori.travelclub.service.logic;

import io.namoosori.travelclub.aggregate.club.TravelClub;
import io.namoosori.travelclub.service.ClubService;
import io.namoosori.travelclub.service.sdo.TravelClubCdo;
import io.namoosori.travelclub.shared.NameValueList;
import io.namoosori.travelclub.store.ClubStore;
import io.namoosori.travelclub.util.exception.ClubDuplicationException;
import io.namoosori.travelclub.util.exception.NoSuchClubException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("clubService")
public class ClubServiceLogic implements ClubService {
	private ClubStore clubStore;

	public ClubServiceLogic(ClubStore clubStore) {
		// IoC로 의해 clubStore 를 객체로 불러오고 의존성 주입이 됨(bean 설정)
		this.clubStore = clubStore;
	}

	@Override
	public String registerClub(TravelClubCdo clubCdo) {
		TravelClub club = new TravelClub(clubCdo.getName(), clubCdo.getIntro());
		club.checkValidation();
		if (clubStore.retrieveByName(club.getName()) != null) {
			throw new ClubDuplicationException("Club already exist with name --> " + club.getName());
		}
		String clubId = clubStore.create(club);
		return clubId;
	}

	@Override
	public TravelClub findClubById(String id) {
		TravelClub foundClub = clubStore.retrieve(id);
		if (foundClub == null) {
			throw new NoSuchClubException("No such club with id --> " + id);
		}
		return foundClub;
	}

	@Override
	public List<TravelClub> findClubsByName(String name) {
		List<TravelClub> foundClubs = clubStore.retrieveByName(name);
		if (foundClubs == null) {
			throw new NoSuchClubException("No such club with name --> " + name);
		}
		return foundClubs;
	}

	@Override
	public List<TravelClub> findAll(){
		List<TravelClub> foundClubs = clubStore.retrieveAll();
		if (foundClubs == null) {
			throw new NoSuchClubException("No such clubs");
		}
		return foundClubs;
	}

	@Override
	public void modify(String clubId, NameValueList nameValueList) {
		TravelClub foundedClub = clubStore.retrieve(clubId);
		if (foundedClub == null) {
			throw new NoSuchClubException("No such club with id " + clubId);
		}
		foundedClub.modifyValues(nameValueList);
		clubStore.update(foundedClub);
	}

	@Override
	public void remove(String clubId) {
		if (!clubStore.exists(clubId)) {
			throw new NoSuchClubException("No such club with id " + clubId);
		}
		clubStore.delete(clubId);
	}
}
