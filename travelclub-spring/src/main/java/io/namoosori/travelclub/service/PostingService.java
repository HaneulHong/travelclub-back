package io.namoosori.travelclub.service;


import io.namoosori.travelclub.aggregate.club.Posting;
import io.namoosori.travelclub.service.sdo.PostingCdo;
import io.namoosori.travelclub.shared.NameValueList;

import java.util.List;

public interface PostingService {
    String register(String boardId, PostingCdo posting);
    Posting find(String postingId);
    List<Posting> findByBoardId(String boardId);
    void modify(String postingId, NameValueList nameValueList);
    void remove(String postingId);
    void removeAllIn(String boardId);
}
