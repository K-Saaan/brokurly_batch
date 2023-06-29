package brokurly.batch.dto.user;

import brokurly.batch.entity.user.UserEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 아무런 값도 갖지 않는 의미 없는 객체의 생성을 막아줌
public class UserDto {
    private String userNm;

    @Builder
    public UserDto(String userNm){
        this.userNm = userNm;
    }

    // DTO -> Entity
    public UserEntity toEntity(){
        return UserEntity.builder()
                .userNm(userNm)
                .build();
    }
}
