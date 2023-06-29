package brokurly.batch.entity.member;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

@Getter
@Entity
@AllArgsConstructor
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
@Table(name = "cust_info", catalog = "cs")
public class MemberEntity {
	@Id
	@Column(name = "CUST_CODE")
	private String custCode;

	@Column(name = "CUST_NM")
	private String custNm;
	@Column(name = "CUST_TEL")
	private String custTel;
	@Column(name = "CUST_EMAIL")
	private String custEmail;
	@Column(name = "CUST_BIRTH")
	private String custBirth;
	@Column(name = "SITE_CODE")
	private String siteCode;
	@Column(name = "CUST_ADDR_DTL")
	private String custAddrDtl;
	@Column(name = "REG_ID")
	private String regId;
	@Column(name = "REG_DATE")
	private String regDate;
	@Column(name = "CHGR_ID")
	private String chgrId;
	@Column(name = "CHGR_DATE")
	private String chgrDate;

	@Transactional
	public void update(String custNm, String custEmail) {
		this.custNm = custNm;
		this.custEmail = custEmail;
	}

	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	public void setCustTel(String custTel) {
		this.custTel = custTel;
	}

	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}

	public void setCustAddrDtl(String custAddrDtl) {
		this.custAddrDtl = custAddrDtl;
	}
}
