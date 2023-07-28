package brokurly.batch.repository.order;

import brokurly.batch.entity.order.DeliLocInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeliLocInfoRepository extends JpaRepository<DeliLocInfoEntity, String>, JpaSpecificationExecutor<DeliLocInfoEntity> {

}
