package brokurly.batch.entity.product;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "qna", catalog="pd")
public class QnaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "QNA_CODE")
    private String qnaCode;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "CUST_CODE")
    private String custCode;
    @Column(name = "PD_CODE")
    private String pdCode;
    @Column(name = "QNA_TITLE")
    private String qnaTitle;
    @Column(name = "QNA_DESC")
    private String qnaDesc;
    @Column(name = "QNA_REPLY")
    private String qnaReply;
    @Column(name = "OPEN_YN")
    private String openYn;
    @Column(name = "WRITE_DATE")
    private String writeDate;
    @Column(name = "QNA_STATE")
    private String qnaState;
    @Column(name = "COM_DATE")
    private String comDate;
    @Column(name = "MNG_ID")
    private String mngId;
    @Column(name = "REG_ID")
    private String regId;
    @Column(name = "REG_DATE")
    private Timestamp regDate;
    @Column(name = "CHGR_ID")
    private String chgrId;
    @Column(name = "CHGR_DATE")
    private Timestamp chgrDate;

    @Transactional
    public void replyQna(String qnaCode, String qnaReply, String qnaState) {
        this.qnaCode = qnaCode;
        this.qnaReply = qnaReply;
        this.qnaState = qnaState;
    }
}
