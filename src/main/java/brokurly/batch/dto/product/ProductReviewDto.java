package brokurly.batch.dto.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
public class ProductReviewDto {
    private String pdCode;
    private String pdNm;
    private String reviewTxt;

    @Builder
    public ProductReviewDto(String pdCode, String pdNm, String reviewTxt) {
        this.pdCode = pdCode;
        this.pdNm = pdNm;
        this.reviewTxt = reviewTxt;
    }
}
