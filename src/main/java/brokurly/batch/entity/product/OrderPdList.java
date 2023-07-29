package brokurly.batch.entity.product;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Embeddable
public class OrderPdList implements Serializable {
    @Column(name = "OD_CODE")
    private String odCode;
    @Column(name = "PD_CODE")
    private String pdCode;
}
