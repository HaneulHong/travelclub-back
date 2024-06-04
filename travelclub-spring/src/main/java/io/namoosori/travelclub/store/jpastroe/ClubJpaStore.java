package io.namoosori.travelclub.store.jpastroe;

import io.namoosori.travelclub.aggregate.club.TravelClub;
import io.namoosori.travelclub.store.ClubStore;
import io.namoosori.travelclub.store.jpastroe.jpo.ClubJpo;
import io.namoosori.travelclub.store.jpastroe.jpo.MemberJpo;
import io.namoosori.travelclub.store.jpastroe.repository.ClubRepository;
import io.namoosori.travelclub.util.exception.NoSuchClubException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class ClubJpaStore implements ClubStore {

    private ClubRepository clubRepository;

    public ClubJpaStore(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public String create(TravelClub club) {
        clubRepository.save(new ClubJpo(club));
        return club.getId();
    }

    @Override
    public TravelClub retrieve(String clubId) {
        Optional<ClubJpo> clubJpo = clubRepository.findById(clubId);

        return clubJpo.map(ClubJpo::toDomain).orElse(null);
    }

    @Override
    public List<TravelClub> retrieveByName(String name) {
        List<ClubJpo> clubJpos = clubRepository.findAllByName(name);
        return clubJpos.stream().map(ClubJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<TravelClub> retrieveAll() {
        List<ClubJpo> clubJpos = clubRepository.findAll();
        // return clubJpos.stream().map(clubJpo -> clubJpo.toDomain()).collect(Collectors.toList());
        return clubJpos.stream().map(ClubJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(TravelClub club) {
        clubRepository.save(new ClubJpo(club));
    }

    @Transactional
    @Override
    public void delete(String clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public boolean exists(String clubId) {
        return clubRepository.existsById(clubId);
    }
}
