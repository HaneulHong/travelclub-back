package io.namoosori.travelclub.service.logic;

import io.namoosori.travelclub.aggregate.club.Posting;
import io.namoosori.travelclub.service.PostingService;
import io.namoosori.travelclub.service.sdo.PostingCdo;
import io.namoosori.travelclub.shared.NameValueList;
import io.namoosori.travelclub.store.BoardStore;
import io.namoosori.travelclub.store.PostingStore;
import io.namoosori.travelclub.util.exception.NoSuchPostingException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostingServiceLogic implements PostingService {
    private PostingStore postingStore;

    public PostingServiceLogic(PostingStore postingStore) {
        this.postingStore = postingStore;
    }

    @Override
    public String register(String boardId, PostingCdo posting) {
        Posting newPosting = new Posting(boardId, posting.getTitle(), posting.getContents(), posting.getWriterEmail());
        String postingId = postingStore.create(newPosting);
        return postingId;
    }

    @Override
    public Posting find(String postingId) {
        Posting foundPosting = postingStore.retrieve(postingId);
        if (foundPosting == null) {
            throw new NoSuchPostingException("No such Posting with id --> " + postingId);
        }
        return foundPosting;
    }

    @Override
    public List<Posting> findByBoardId(String boardId) {
        List<Posting> foundPostings = postingStore.retrieveByBoardId(boardId);
        if (foundPostings == null) {
            throw new NoSuchPostingException("No such Posting with boardId --> " + boardId);
        }
        return foundPostings;
    }

    @Override
    public void modify(String postingId, NameValueList nameValueList) {
        Posting foundPosting = postingStore.retrieve(postingId);
        if (foundPosting == null) {
            throw new NoSuchPostingException("No such posting with id --> " + postingId);
        }
        foundPosting.modifyValues(nameValueList);
        postingStore.update(foundPosting);
    }

    @Override
    public void remove(String postingId) {
        if (!postingStore.exists(postingId)) {
            throw new NoSuchPostingException("No such posting with id --> " + postingId);
        }

        postingStore.delete(postingId);
    }

    @Override
    public void removeAllIn(String boardId) {
        List<Posting> postingList = new ArrayList<>(postingStore.retrieveByBoardId(boardId));
        if (postingList.isEmpty()) {
            throw new NoSuchPostingException("No such posting list");
        }
        postingStore.retrieveByBoardId(boardId).stream()
                .forEach(posting -> postingStore.delete(posting.getId()));
    }
}
