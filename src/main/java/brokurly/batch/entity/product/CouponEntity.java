package brokurly.batch.entity.product;

import lombok.*;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "cpn_info", catalog="pd")
public class CouponEntity {
    @Id
    @Column(name = "CPN_CODE")
    private String cpnCode;
    @Column(name = "CPN_GUBUN")
    private String cpnGubun;
    @Column(name = "CPN_NM")
    private String cpnNm;
    @Column(name = "CPN_STAT")
    private String cpnStat;
    @Column(name = "START_DATE")
    private String startDate;
    @Column(name = "END_DATE")
    private String endDate;
    @Column(name = "CPN_PRICE", precision = 10, scale = 0)
    private BigDecimal cpnPrice;
    @Column(name = "CPN_RATIO", precision = 10, scale = 0)
    private BigDecimal cpnRatio;
    @Column(name = "MIN_OD_AMT", precision = 10, scale = 0)
    private BigDecimal minOdAmt;
    @Column(name = "MAX_AMT", precision = 10, scale = 0)
    private BigDecimal maxAmt;
    @Column(name = "DTL_DESC")
    private String dtlDesc;
    @Column(name = "USE_REQ")
    private String useReq;
    @Column(name = "REG_ID")
    private String regId;
    @Column(name = "REG_DATE")
    private Timestamp regDate;
    @Column(name = "CHGR_ID")
    private String chgrId;
    @Column(name = "CHGR_DATE")
    private Timestamp chgrDate;

    @Transactional
    public void modCpn(String cpnCode, String cpnGubun, String cpnNm, String cpnStat, String startDate,
                       String endDate, BigDecimal cpnPrice, BigDecimal cpnRatio, BigDecimal minOdAmt, BigDecimal maxAmt,
                       String dtlDesc, String useReq, String chgrId, Timestamp chgrDate) {
        this.cpnCode = cpnCode;
        this.cpnGubun = cpnGubun;
        this.cpnNm = cpnNm;
        this.cpnStat = cpnStat;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cpnPrice = cpnPrice;
        this.cpnRatio = cpnRatio;
        this.minOdAmt = minOdAmt;
        this.maxAmt = maxAmt;
        this.dtlDesc = dtlDesc;
        this.useReq = useReq;
        this.chgrId = chgrId;
        this.chgrDate = chgrDate;
    }
}
