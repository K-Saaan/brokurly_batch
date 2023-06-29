package brokurly.batch.entity.common;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@ToString
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
public class ComCodeList implements Serializable {

    @Column(name = "COM_CD_GRP_ID")
    private String comCdGrpId;

}
