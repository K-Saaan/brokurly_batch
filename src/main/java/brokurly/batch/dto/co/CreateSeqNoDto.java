package brokurly.batch.dto.co;

import brokurly.batch.entity.common.SequenceEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
public class CreateSeqNoDto {
    public String tblCode;
    public int currNo;

    @Builder
    public CreateSeqNoDto(String tblCode, int currNo){
        this.tblCode = tblCode;
        this.currNo = currNo;
    }

    public SequenceEntity toEntity(){
        return SequenceEntity.builder()
                .tblCode(tblCode)
                .currNo(currNo)
                .build();
    }
}
