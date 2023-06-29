package brokurly.batch.dto.product;

import brokurly.batch.entity.product.QnaEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
public class QnaDto {
    private String qnaCode;
    private String qnaReply;
    private String qnaState;

    @Builder
    public QnaDto(String qnaCode, String qnaReply, String qnaState) {
        this.qnaCode = qnaCode;
        this.qnaReply = qnaReply;
        this.qnaState = qnaState;
    }
    // DTO -> ENTITY
    public QnaEntity toEntity() {
        return QnaEntity.builder()
                .qnaCode(qnaCode)
                .qnaReply(qnaReply)
                .qnaState(qnaState)
                .build();
    }
}
