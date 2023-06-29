package brokurly.batch.entity.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
@Table(name = "cust_info_dtl", catalog = "cs")
public class MemberDtlEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUST_CODE")
	private String custCode;
	
	@Column(name = "AGRE_TERMS")
	private String agreTerms;
	@Column(name = "AGRE_PRIVACY_ESS")
	private String agrePrivacyEss;
	@Column(name = "AGRE_PRIVACY_OPT")
	private String agrePrivacyOpt;
	@Column(name = "AGRE_SMS")
	private String agreSms;
	@Column(name = "AGRE_EMAIL")
	private String agreEmail;
	@Column(name = "AGE_YN")
	private String ageYn;
	@Column(name = "REG_ID")
	private String regID;
	@Column(name = "REG_DATE")
	private Timestamp regDate;
	@Column(name = "CHGR_ID")
	private String chgrId;
	@Column(name = "CHGR_DATE")
	private Timestamp chgrDate;
}
