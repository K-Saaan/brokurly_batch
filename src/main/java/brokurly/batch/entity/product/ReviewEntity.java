package brokurly.batch.entity.product;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review", catalog="pd")
public class ReviewEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REVIEW_SEQ_NO")
	private int reviewSeqNo;

	@Column(name = "USER_ID")
	private String userId;
	@Column(name = "CUST_CODE")
	private String custCode;
	@Column(name = "CUST_NM")
	private String custNm;
	@Column(name = "PD_CODE")
	private String pdCode;
	@Column(name = "PD_NM")
	private String pdNm;
	@Column(name = "REVIEW_DATE")
	private String reviewDate;
	@Column(name = "REVIEW_TXT")
	private String reviewTxt;
	@Column(name = "REVIEW_LIKE")
	private int reviewLike;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "REG_DATE")
	private Timestamp regDate;
	@Column(name = "CHGR_ID")
	private String chgrId;
	@Column(name = "CHGR_DATE")
	private Timestamp chgrDate;

	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}
}
