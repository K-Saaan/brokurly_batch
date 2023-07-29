package brokurly.batch.dto.od;

import brokurly.batch.entity.common.ComCodeEntity;
import brokurly.batch.entity.order.OrderEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
public class ReserveStatDto {
    public String reserveYn;

    @Builder
    public ReserveStatDto(String reserveYn){
        this.reserveYn = reserveYn;
    }

    public OrderEntity toEntity() {
        return OrderEntity.builder()
                .reserveYn(reserveYn).build();
    }
}
