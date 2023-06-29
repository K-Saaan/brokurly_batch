package brokurly.batch.dto.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
public class CouponDto {
    private String cpnCode;
    private String cpnGubun;
    private String cpnNm;
    private String cpnStat;
    private String startDate;
    private String endDate;
    private BigDecimal cpnPrice;
    private BigDecimal cpnRatio;
    private BigDecimal minOdAmt;
    private BigDecimal maxAmt;
    private String dtlDesc;
    private String useReq;
    private String chgrId;
    private Timestamp chgrDate;

    @Builder
    public CouponDto(String cpnCode, String cpnGubun, String cpnNm, String cpnStat, String startDate, String endDate,
                     BigDecimal cpnPrice, BigDecimal cpnRatio, BigDecimal minOdAmt, BigDecimal maxAmt, String dtlDesc,
                     String useReq, String chgrId, Timestamp chgrDate) {
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
