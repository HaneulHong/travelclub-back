package io.namoosori.travelclub.store.jpastroe.jpo;

import io.namoosori.travelclub.aggregate.club.Board;
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
@Table(name = "Board")
public class BoardJpo {

    @Id
    private String id;
    private int sequence;
    private String name;
    private String adminEmail;
    private String createDate;

    private String clubName;

    public BoardJpo(Board board) {
        BeanUtils.copyProperties(board, this);
    }

    public Board toDomain() {
        Board board = new Board(this.name, this.adminEmail);
        board.setId(this.id);
        board.setCreateDate(this.createDate);
        board.setClubName(this.clubName);

        return board;
    }
}
