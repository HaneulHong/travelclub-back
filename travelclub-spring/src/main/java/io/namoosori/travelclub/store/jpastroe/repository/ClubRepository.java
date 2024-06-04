package io.namoosori.travelclub.store.jpastroe.repository;

import io.namoosori.travelclub.store.jpastroe.jpo.ClubJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.*;
import java.util.List;
import java.util.Optional;

public interface ClubRepository extends JpaRepository<ClubJpo, String> {
    List<ClubJpo> findAllByName(String name);
}
