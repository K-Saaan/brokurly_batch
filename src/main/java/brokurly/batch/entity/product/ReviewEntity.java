package brokurly.batch.entity.product;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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
	@Column(name = "PD_CODE")
	private String pdCode;
	@Column(name = "REVIEW_TYP")
	private String reviewTyp;
	@Column(name = "REVIEW_DATE")
	private String reviewDate;
	@Column(name = "REVIEW_TXT")
	private String reviewTxt;
	@Column(name = "REVIEW_LIKE")
	private int reviewLike;
	@Column(name = "RESERVE_YN")
	private String reserveYn;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "REG_DATE")
	private Timestamp regDate;
	@Column(name = "CHGR_ID")
	private String chgrId;
	@Column(name = "CHGR_DATE")
	private Timestamp chgrDate;

	@Transactional
	public void updateReserveYn(int reviewSeqNo, String reserveYn){
		this.reviewSeqNo = reviewSeqNo;
		this.reserveYn = reserveYn;
	}
}
