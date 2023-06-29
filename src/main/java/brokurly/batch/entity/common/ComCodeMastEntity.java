package brokurly.batch.entity.common;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Entity
@AllArgsConstructor
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "com_code_mast", catalog="co")
public class ComCodeMastEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COM_CD_GRP_ID")
    private String comCdGrpId;

    @Column(name = "COM_CD_GRP_NM")
    private String comCdGrpNm;

    @Column(name = "NOTE")
    private String note;

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

    /*@OneToMany(mappedBy = "comCodeMastEntity")
    private List<ComCodeEntity> comCodeEntityList = new ArrayList<>();
    */
}
