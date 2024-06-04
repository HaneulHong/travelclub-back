package io.namoosori.travelclub.store.jpastroe.repository;

import io.namoosori.travelclub.aggregate.club.Posting;
import io.namoosori.travelclub.store.jpastroe.jpo.PostingJpo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostingRepository extends JpaRepository<PostingJpo, String> {
    List<PostingJpo> findByBoardId(String boardId);
    List<PostingJpo> findByTitle(String title);
}
