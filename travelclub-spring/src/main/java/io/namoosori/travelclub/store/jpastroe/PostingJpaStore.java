package io.namoosori.travelclub.store.jpastroe;

import io.namoosori.travelclub.aggregate.club.Posting;
import io.namoosori.travelclub.store.PostingStore;
import io.namoosori.travelclub.store.jpastroe.jpo.PostingJpo;
import io.namoosori.travelclub.store.jpastroe.repository.PostingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostingJpaStore implements PostingStore {

    private PostingRepository postingRepository;

    public PostingJpaStore(PostingRepository postingRepository) {
        this.postingRepository = postingRepository;
    }

    @Override
    public String create(Posting posting) {
        postingRepository.save(new PostingJpo(posting));

        return posting.getId();
    }

    @Override
    public Posting retrieve(String postingId) {
        Optional<PostingJpo> postingJpo = postingRepository.findById(postingId);

        return postingJpo.get().toDomain();
    }

    @Override
    public List<Posting> retrieveByBoardId(String boardId) {
        List<PostingJpo> postingJpos = postingRepository.findByBoardId(boardId);

        return postingJpos.stream().map(PostingJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Posting> retrieveByTitle(String title) {
        List<PostingJpo> postingJpos = postingRepository.findByTitle(title);

        return postingJpos.stream().map(PostingJpo::toDomain).collect(Collectors.toList());
    }

    @Override
    public void update(Posting posting) {
        postingRepository.save(new PostingJpo(posting));
    }

    @Override
    public void delete(String posingId) {
        postingRepository.deleteById(posingId);
    }

    @Override
    public boolean exists(String postingId) {
        return postingRepository.existsById(postingId);
    }
}
