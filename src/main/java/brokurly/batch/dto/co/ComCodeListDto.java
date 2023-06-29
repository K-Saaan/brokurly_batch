package brokurly.batch.dto.co;

import brokurly.batch.entity.common.ComCodeEntity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
public class ComCodeListDto {

    public String comCdNm;

    @Builder
    public ComCodeListDto(String comCdNm){
        this.comCdNm = comCdNm;
    }

    public ComCodeEntity toEntity() {
        return ComCodeEntity.builder()
                .comCdNm(comCdNm).build();
    }
}
