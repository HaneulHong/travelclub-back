package io.namoosori.travelclub.store;


import io.namoosori.travelclub.aggregate.club.Posting;

import java.util.List;

public interface PostingStore {
    String create(Posting posting);
    Posting retrieve(String postingId);
    List<Posting> retrieveByBoardId(String boardId);
    List<Posting> retrieveByTitle(String title);
    void update(Posting posting);
    void delete(String posingId);

    boolean exists(String postingId);
}
