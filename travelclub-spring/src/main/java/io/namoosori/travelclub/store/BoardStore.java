package io.namoosori.travelclub.store;


import io.namoosori.travelclub.aggregate.club.Board;

import java.util.List;

public interface BoardStore {
    String create(Board board);
    Board retrieve(String boardId);
    List<Board> retrieveAll();
    List<Board> retrieveByName(String name);
    List<Board> retrieveByClubName(String name);
    void update(Board board);
    void delete(String boardId);

    boolean exists(String boardId);
}
