package brokurly.batch.dto.co;

import brokurly.batch.entity.common.SequenceEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
public class updateSeqDto {
    public String tblCode;
    public int currNo;
    public String currYyyy;
    public String currMm;
    public String currDd;

    @Builder
    public updateSeqDto(String tblCode, int currNo, String currYyyy, String currMm, String currDd){
        this.tblCode = tblCode;
        this.currNo = currNo;
        this.currYyyy = currYyyy;
        this.currMm = currMm;
        this.currDd = currDd;
    }

    public SequenceEntity toEntity(){
        return SequenceEntity.builder()
                .tblCode(tblCode)
                .currNo(currNo)
                .currYyyy(currYyyy)
                .currMm(currMm)
                .currDd(currDd)
                .build();
    }
}
