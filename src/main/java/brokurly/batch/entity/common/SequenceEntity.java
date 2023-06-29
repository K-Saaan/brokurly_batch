package brokurly.batch.entity.common;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "sequence_info", catalog = "co")
public class SequenceEntity {

    @Id
    @Column(name = "TBL_CODE")
    private String tblCode;

    @Column(name = "TBL_CODE_VAL")
    private String tblCodeVal;

    @Column(name = "MIN_NO")
    private int minNo;

    @Column(name = "MAX_NO")
    private int maxNo;

    @Column(name = "CURR_NO")
    private int currNo;

    @Column(name = "CURR_YYYY")
    private String currYyyy;

    @Column(name = "CURR_MM")
    private String currMm;

    @Column(name = "CURR_DD")
    private String currDd;

    @Transactional
    public void CreateSeqDateDto(String tblCode, int currNo, String currYyyy, String currMm, String currDd){
        this.tblCode = tblCode;
        this.currNo = currNo;
        this.currYyyy = currYyyy;
        this.currMm = currMm;
        this.currDd = currDd;
    }

    @Transactional
    public void CreateSeqNoDto(String tblCode, int currNo){
        this.tblCode = tblCode;
        this.currNo = currNo;
    }

    @Transactional
    public void updateSeqDto(String tblCode, int currNo, String currYyyy, String currMm, String currDd){
        this.tblCode = tblCode;
        this.currNo = currNo;
        this.currYyyy = currYyyy;
        this.currMm = currMm;
        this.currDd = currDd;
    }
}
