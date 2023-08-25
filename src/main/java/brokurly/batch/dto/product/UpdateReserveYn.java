package brokurly.batch.dto.product;

import brokurly.batch.entity.product.ReviewEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateReserveYn {
    public int reviewSeqNo;
    public String reserveYn;

    @Builder
    public UpdateReserveYn(int reviewSeqNo, String reserveYn){
        this.reviewSeqNo = reviewSeqNo;
        this.reserveYn = reserveYn;
    }

    public ReviewEntity toEntity() {
        return ReviewEntity.builder()
                .reviewSeqNo(reviewSeqNo)
                .reserveYn(reserveYn)
                .build();
    }
}
