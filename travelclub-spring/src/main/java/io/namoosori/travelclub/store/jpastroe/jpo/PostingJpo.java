package io.namoosori.travelclub.store.jpastroe.jpo;

import io.namoosori.travelclub.aggregate.club.Posting;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Posting")
public class PostingJpo {

    @Id
    private String id;
    private String title;
    private String writerEmail;
    private String contents;
    private String writtenDate;
    private int readCount;

    private String boardId;

    public PostingJpo(Posting posting) {
        BeanUtils.copyProperties(posting, this);
    }

    public Posting toDomain() {
        Posting posting = new Posting(this.id);
        posting.setBoardId(this.boardId);
        posting.setTitle(this.title);
        posting.setContents(this.contents);
        posting.setWriterEmail(this.writerEmail);
        posting.setWrittenDate(this.writtenDate);
        posting.setReadCount(this.readCount);

        return posting;
    }
}
