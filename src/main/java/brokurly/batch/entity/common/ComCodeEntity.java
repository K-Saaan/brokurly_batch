package brokurly.batch.entity.common;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Getter
@Entity
@AllArgsConstructor
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "com_code", catalog="co")
public class ComCodeEntity {
//	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)

	@EmbeddedId
	private ComCodeList comCodeList;

//	@Column(name = "COM_CD_GRP_ID")
//	private String comCdGrpId;

	@Column(name = "COM_CD")
	private String comCd;
	@Column(name = "COM_CD_NM", insertable = false, updatable = false)
	private String comCdNm;
	@Column(name = "INDEX")
	private int index;
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
