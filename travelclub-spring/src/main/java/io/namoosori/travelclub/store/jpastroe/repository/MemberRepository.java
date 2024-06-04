package io.namoosori.travelclub.store.jpastroe.repository;

import io.namoosori.travelclub.store.jpastroe.jpo.MemberJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberJpo, String> {
    void deleteByEmail(String email);
    Optional<MemberJpo> findByEmail(String email);
    List<MemberJpo> findByName(String name);
}
