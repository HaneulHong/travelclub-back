package io.namoosori.travelclub.service.logic;

import io.namoosori.travelclub.aggregate.club.Board;
import io.namoosori.travelclub.service.BoardService;
import io.namoosori.travelclub.service.sdo.BoardCdo;
import io.namoosori.travelclub.shared.NameValueList;
import io.namoosori.travelclub.store.BoardStore;
import io.namoosori.travelclub.util.exception.NoSuchBoardException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardServiceLogic implements BoardService {
    private BoardStore boardStore;

    public BoardServiceLogic(BoardStore boardStore) {
        this.boardStore = boardStore;
    }

    @Override
    public String register(BoardCdo board) {
        Board newBoard = new Board(board.getName(), board.getAdminEmail());
        String boardId = boardStore.create(newBoard);
        return boardId;
    }

    @Override
    public List<Board> findAll() {
        return boardStore.retrieveAll();
    }

    @Override
    public Board find(String boardId) {
        return boardStore.retrieve(boardId);
    }

    @Override
    public List<Board> findByName(String boardName) {
        return boardStore.retrieveByName(boardName);
    }

    @Override
    public List<Board> findByClubName(String clubName) {
        return boardStore.retrieveByClubName(clubName);
    }

    @Override
    public void modify(String boardId, NameValueList nameValueList) {
        Board foundBoard = boardStore.retrieve(boardId);
        if (foundBoard == null) {
            throw new NoSuchBoardException("No such board with id --> " + boardId);
        }
        foundBoard.modifyValues(nameValueList);
        boardStore.update(foundBoard);
    }

    @Override
    public void remove(String boardId) {
        if (!boardStore.exists(boardId)) {
            throw new NoSuchBoardException("No such board with id --> " + boardId);
        }
        boardStore.delete(boardId);
    }
}
