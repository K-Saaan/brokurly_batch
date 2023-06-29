package brokurly.batch.entity.user;

import lombok.*;

import javax.persistence.*;

@Getter
@Entity
@Builder
@AllArgsConstructor
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_info", catalog = "cs")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    @Column(name = "CUST_CODE")
    private String custCode;

    @Column(name = "USER_PWD")
    private String userPwd;

    @Column(name = "USER_NM")
    private String userNm;

    @Column(name = "PWD_EXP_DATE")
    private String pwdExpDate;

    @Column(name = "LOGIN_FAIL_CNT")
    private String loginFailCnt;

    @Column(name = "USER_STAT")
    private String userStat;

    @Column(name = "CRTE_DTTM")
    private String crteDttm;

    @Column(name = "QUIT_DTTM")
    private String quitDttm;

    @Column(name = "QUIT_RSN")
    private String quitRsn;

    @Column(name = "REG_ID")
    private String regId;

    @Column(name = "REG_DATE")
    private String regDate;

    @Column(name = "CHGR_ID")
    private String chgrId;

    @Column(name = "CHGR_DATE")
    private String chgrDate;


    public void UpdateUserPwd(String userNm){
        this.userNm = userNm;
    }

    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
}
