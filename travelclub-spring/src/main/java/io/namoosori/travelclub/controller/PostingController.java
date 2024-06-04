package io.namoosori.travelclub.controller;

import io.namoosori.travelclub.aggregate.club.Posting;
import io.namoosori.travelclub.service.PostingService;
import io.namoosori.travelclub.service.sdo.PostingCdo;
import io.namoosori.travelclub.shared.NameValueList;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posting")
public class PostingController {
    private PostingService postingService;

    public PostingController(PostingService postingService) {
        this.postingService = postingService;
    }

    @PostMapping
    public String register(@RequestBody String boardId, PostingCdo posting) {
        return postingService.register(boardId, posting);
    }

    @GetMapping("/{postingId}")
    public Posting find(@PathVariable String postingId) {
        return postingService.find(postingId);
    }

    @GetMapping("/All/{boardId}")
    public List<Posting> findByBoardId(@PathVariable String boardId) {
        return postingService.findByBoardId(boardId);
    }

    @PutMapping("/{postingId}")
    public void modify(@PathVariable String postingId, @RequestBody NameValueList nameValueList) {
        postingService.modify(postingId, nameValueList);
    }

    @DeleteMapping("/{postingId}")
    public void remove(@PathVariable String postingId) {
        postingService.remove(postingId);
    }

    @DeleteMapping("/All/{boardId}")
    public void removeAllIn(@PathVariable String boardId) {
        postingService.removeAllIn(boardId);
    }
}
