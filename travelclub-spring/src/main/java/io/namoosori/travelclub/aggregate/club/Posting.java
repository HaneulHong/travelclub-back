package io.namoosori.travelclub.aggregate.club;

import io.namoosori.travelclub.aggregate.Entity;
import io.namoosori.travelclub.shared.NameValue;
import io.namoosori.travelclub.shared.NameValueList;
import io.namoosori.travelclub.util.helper.DateUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class Posting extends Entity {
    private String title;
    private String writerEmail;
    private String contents;
    private String writtenDate;
    private int readCount;

    private String boardId;

    public Posting(String id) {
        super(id);
        this.readCount = 0;
    }

    public Posting(Board board, String title, String writerEmail, String contents) {
        super();
        this.boardId = board.getId();
        this.title = title;
        this.writerEmail = writerEmail;
        this.contents = contents;
        this.writtenDate = DateUtil.today();
    }

    public Posting(String boardId, String title, String writerEmail, String contents) {
        this.title = title;
        this.writerEmail = writerEmail;
        this.contents = contents;
        this.boardId = boardId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        sb.append("Posting Id : ").append(id);
        sb.append(", title : ").append(title);
        sb.append(", writerEmail : ").append(writerEmail);
        sb.append(", contents : ").append(contents);
        sb.append(", writtenDate : ").append(writtenDate);
        sb.append(", read count : ").append(readCount);
        sb.append(", boardId : ").append(boardId);

        return sb.toString();
    }

    public void modifyValues(NameValueList nameValues) {
        for (NameValue nameValue : nameValues.getNameValues()) {
            String value = nameValue.getValue();
            switch (nameValue.getName()) {
                case "name":
                    this.title = value;
                    break;
                case "intro":
                    this.contents = value;
                    break;
            }
        }
    }

}
