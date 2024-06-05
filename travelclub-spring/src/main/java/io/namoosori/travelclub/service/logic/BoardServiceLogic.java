package io.namoosori.travelclub.service.logic;

import io.namoosori.travelclub.aggregate.club.Board;
import io.namoosori.travelclub.service.BoardService;
import io.namoosori.travelclub.service.sdo.BoardCdo;
import io.namoosori.travelclub.shared.NameValueList;
import io.namoosori.travelclub.store.BoardStore;
import io.namoosori.travelclub.util.exception.BoardDuplicationException;
import io.namoosori.travelclub.util.exception.NoSuchBoardException;
import io.namoosori.travelclub.util.exception.NoSuchMembershipException;
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

        if (boardStore.retrieveByName(board.getName()) != null) {
            throw new BoardDuplicationException("Board already exists for club with name " + board.getName());
        }

        Board newBoard = new Board(board.getName(), board.getAdminEmail());
        String boardId = boardStore.create(newBoard);
        return boardId;
    }

    @Override
    public List<Board> findAll() {
        List<Board> foundBoards = boardStore.retrieveAll();
        if (foundBoards.isEmpty()) {
            throw new NoSuchBoardException("No such boards");
        }
        return foundBoards;
    }

    @Override
    public Board find(String boardId) {
        Board foundBoard = boardStore.retrieve(boardId);
        if (foundBoard == null) {
            throw new NoSuchBoardException("No such board with id --> " + boardId);
        }
        return foundBoard;
    }

    @Override
    public List<Board> findByName(String boardName) {
        List<Board> foundBoards = boardStore.retrieveByName(boardName);

        if (foundBoards == null) {
            throw new NoSuchBoardException("No such boards with name --> " + boardName);
        }

        return foundBoards;
    }

    @Override
    public List<Board> findByClubName(String clubName) {
        List<Board> foundBoards = boardStore.retrieveByClubName(clubName);
        if (foundBoards == null) {
            throw new NoSuchBoardException("No such boards with clubNane --> " + clubName);
        }
        return foundBoards;
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
