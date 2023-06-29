package brokurly.batch.dto.member;

import brokurly.batch.entity.member.MemberEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
public class MemberDto {
	private String custNm;
	private String custEmail;
	
	@Builder
	public MemberDto(String custNm, String custEmail) {
		this.custNm = custNm;
		this.custEmail = custEmail;
	}
	
	// DTO -> ENTITY
	public MemberEntity toEntity() {
		return MemberEntity.builder()
				.custNm(custNm)
				.custEmail(custEmail)
				.build();
	}
}
