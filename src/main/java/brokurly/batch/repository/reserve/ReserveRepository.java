package brokurly.batch.repository.reserve;

import brokurly.batch.entity.product.CouponEntity;
import brokurly.batch.entity.reserve.ReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends JpaRepository<ReserveEntity, Integer>, JpaSpecificationExecutor<ReserveEntity> {
}
