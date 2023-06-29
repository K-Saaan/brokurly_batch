package brokurly.batch.entity.etc;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "notice_info", catalog="co")
public class NoticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTICE_SEQ_NO")
    private int noticeSeqNo;
    @Column(name = "NOTICE_TITLE")
    private String noticeTitle;
    @Column(name = "NOTICE_WRITER")
    private String noticeWriter;
    @Column(name = "NOTICE_DT")
    private String noticeDt;
    @Column(name = "NOTICE_DESC")
    private String noticeDesc;
    @Column(name = "NOTICE_STAT")
    private String noticeStat;
    @Column(name = "OPEN_YN")
    private String openYn;
    @Column(name = "REG_ID")
    private String regId;
    @Column(name = "REG_DATE")
    private Timestamp regDate;
    @Column(name = "CHGR_ID")
    private String chgrId;
    @Column(name = "CHGR_DATE")
    private Timestamp chgrDate;
}
