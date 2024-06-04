package io.namoosori.travelclub.store.jpastroe;

import io.namoosori.travelclub.aggregate.club.Board;
import io.namoosori.travelclub.store.BoardStore;
import io.namoosori.travelclub.store.jpastroe.jpo.BoardJpo;
import io.namoosori.travelclub.store.jpastroe.repository.BoardRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BoardJpaStore implements BoardStore {

    private BoardRepository boardRepository;

    public BoardJpaStore(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public String create(Board board) {
        boardRepository.save(new BoardJpo(board));

        return board.getId();
    }

    @Override
    public Board retrieve(String boardId) {
        Optional<BoardJpo> boardJpo = boardRepository.findById(boardId);

        return boardJpo.get().toDomain();
    }

    @Override
    public List<Board> retrieveAll() {
        List<BoardJpo>  boardJpos = boardRepository.findAll();

        return boardJpos.stream().map(BoardJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Board> retrieveByName(String name) {
        List<BoardJpo> boardJpos = boardRepository.findByName(name);

        return boardJpos.stream().map(BoardJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Board> retrieveByClubName(String name) {
        List<BoardJpo> boardJpos = boardRepository.findByClubName(name);

        return boardJpos.stream().map(BoardJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(Board board) {
        boardRepository.save(new BoardJpo(board));
    }

    @Override
    public void delete(String boardId) {
        boardRepository.deleteById(boardId);
    }

    @Override
    public boolean exists(String boardId) {
        return boardRepository.existsById(boardId);
    }
}
