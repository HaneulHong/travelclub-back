package io.namoosori.travelclub.controller;

import io.namoosori.travelclub.aggregate.club.Board;
import io.namoosori.travelclub.service.BoardService;
import io.namoosori.travelclub.service.sdo.BoardCdo;
import io.namoosori.travelclub.shared.NameValueList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping
    public String register(@RequestBody BoardCdo board) {
        return boardService.register(board);
    }

    @GetMapping("all")
    public List<Board> findAll() {
        return boardService.findAll();
    }

    @GetMapping("/{boardId}")
    public Board find(@PathVariable String boardId) {
        return boardService.find(boardId);
    }

    @GetMapping
    public List<Board> findByName(@RequestParam String boardName) {
        return boardService.findByName(boardName);
    }

    @GetMapping("/club/{clubName}")
    public List<Board> findByClubName(@PathVariable String clubName) {
        return boardService.findByClubName(clubName);
    }

    @PutMapping("/{boardId}")
    public void modify(@PathVariable String boardId, @RequestBody NameValueList nameValueList) {
        boardService.modify(boardId, nameValueList);
    }

    @DeleteMapping("/{boardId}")
    public void remove(@PathVariable String boardId){
        boardService.remove(boardId);
    }
}
