package brokurly.batch.dto.product;

import brokurly.batch.entity.product.ReviewEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
public class ReviewDto {

    private String custNm;


    @Builder
    public ReviewDto(String custNm) {
        this.custNm = custNm;
    }

    // DTO -> ENTITY
    public ReviewEntity toEntity() {
        return ReviewEntity.builder()
                .custNm(custNm)
                .build();
    }
}
