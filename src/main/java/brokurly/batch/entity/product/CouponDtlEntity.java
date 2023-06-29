package brokurly.batch.entity.product;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Getter
@Entity
@Builder
@AllArgsConstructor
@IdClass(CouponList.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
@Table(name = "cpn_dtl", catalog = "pd")
public class CouponDtlEntity implements Serializable {
    @Id
    @Column(name = "CPN_CODE")
    private String cpnCode;
    @Id
    @Column(name = "PD_CODE")
    private String pdCode;
    @Column(name = "USE_YN")
    private String useYn;
    @Column(name = "REG_ID")
    private String regId;
    @Column(name = "REG_DATE")
    private Timestamp regDate;
    @Column(name = "CHGR_ID")
    private String chgrId;
    @Column(name = "CHGR_DATE")
    private Timestamp chgrDate;
}
