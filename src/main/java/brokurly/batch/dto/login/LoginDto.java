package brokurly.batch.dto.login;

import brokurly.batch.entity.mng.MngEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
public class LoginDto {
	
	private int loginFailCnt;
	private String acntLock;
	private String chgrId;
	private String chgrDate;
	
	
	
	@Builder
	public LoginDto(int loginFailCnt, String acntLock,String chgrId,String chgrDate) {
		this.loginFailCnt = loginFailCnt;
		this.acntLock = acntLock;
		this.chgrId = chgrId;
		this.chgrDate = chgrDate;
	}
	
	// DTO -> ENTITY
	public MngEntity toEntity() {
		return MngEntity.builder()
				.loginFailCnt(loginFailCnt)
				.acntLock(acntLock)
				.chgrId(chgrId)
				.chgrDate(chgrDate)
				.build();
	}
}
