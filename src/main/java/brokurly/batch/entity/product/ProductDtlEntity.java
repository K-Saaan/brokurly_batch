package brokurly.batch.entity.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
@Table(name = "pd_info_dtl", catalog = "pd")
public class ProductDtlEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PD_CODE")
	private String pdCode;
	
	@Column(name = "DISC_CODE")
	private String discCode;
	@Column(name = "CPN_CODE")
	private String cpnCode;
	@Column(name = "DISC_YN")
	private String discYn;
	@Column(name = "CPN_YN")
	private String cpnYn;
	@Column(name = "RESERVE_YN")
	private String reserveYn;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "REG_DATE")
	private String regDate;
	@Column(name = "CHGR_ID")
	private String chgrId;
	@Column(name = "CHGR_DATE")
	private String chgrDate;
}
