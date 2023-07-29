package brokurly.batch.entity.reserve;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "reserve_amt_info", catalog="cs")
public class ReserveEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVE_SEQ_NO")
    private int reserveSeqNo;
    @Column(name = "CUST_CODE")
    private String custCode;
    @Column(name = "RESERVE_TYP")
    private String reserveTyp;
    @Column(name = "RESERVE_DT")
    private String reserveDt;
    @Column(name = "RESERVE_AMT")
    private double reserveAmt;
    @Column(name = "RESERVE_DESC")
    private String reserveDesc;
    @Column(name = "REG_ID")
    private String regId;
    @Column(name = "REG_DATE")
    private LocalDateTime regDate;
    @Column(name = "CHGR_ID")
    private String chgrId;
    @Column(name = "CHGR_DATE")
    private LocalDateTime chgrDate;
}
