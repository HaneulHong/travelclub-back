package io.namoosori.travelclub.store.jpastroe.repository;

import io.namoosori.travelclub.store.jpastroe.jpo.BoardJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<BoardJpo, String> {
    List<BoardJpo> findByName(String name);
    List<BoardJpo> findByClubName(String clubName);
}
