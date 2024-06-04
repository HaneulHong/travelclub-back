package io.namoosori.travelclub.service;

import io.namoosori.travelclub.aggregate.club.Board;
import io.namoosori.travelclub.service.sdo.BoardCdo;
import io.namoosori.travelclub.shared.NameValueList;

import java.util.List;

public interface BoardService {
    String register(BoardCdo board);
    List<Board> findAll();
    Board find(String boardId);
    List<Board> findByName(String boardName);
    List<Board> findByClubName(String clubName);
    void modify(String boardId, NameValueList nameValueList);
    void remove(String boardId);
}
