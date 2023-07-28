package brokurly.batch.entity.order;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "deli_loc_info", catalog="od")
public class DeliLocInfoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DELI_LOC_CODE")
    private String deliLocCode;
    @Column(name = "CUST_CODE")
    private String custCode;
    @Column(name = "SITE_CODE")
    private String siteCode;
    @Column(name = "DELI_LOC_NM")
    private String deliLocNm;
    @Column(name = "SITE_DTL")
    private String siteDtl;
    @Column(name = "REG_ID")
    private String regId;
    @Column(name = "REG_DATE")
    private LocalDateTime regDate;
    @Column(name = "CHGR_ID")
    private String chgrId;
    @Column(name = "CHGR_DATE")
    private LocalDateTime chgrDate;
}
