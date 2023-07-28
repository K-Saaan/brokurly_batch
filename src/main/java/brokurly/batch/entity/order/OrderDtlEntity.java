package brokurly.batch.entity.order;

import brokurly.batch.entity.product.CouponList;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Builder
@AllArgsConstructor
@IdClass(CouponList.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "od_info_dtl", catalog="od")
public class OrderDtlEntity implements Serializable {
    @Id
    @Column(name = "OD_CODE")
    private String odCode;
    @Id
    @Column(name = "PD_CODE")
    private String pdCode;
    @Column(name = "PD_CNT")
    private String pdCnt;
    @Column(name = "PD_OPT_CODE")
    private String pdOptCode;
    @Column(name = "PD_DISC_AMT")
    private String pdDiscAmt;
    @Column(name = "CPN_CODE")
    private String cpnCode;
    @Column(name = "CPN_DISC_AMT")
    private String cpnDiscAmt;
}
