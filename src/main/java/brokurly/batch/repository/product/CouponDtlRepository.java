package brokurly.batch.repository.product;

import brokurly.batch.entity.product.CouponDtlEntity;
import brokurly.batch.entity.product.CouponList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CouponDtlRepository extends JpaRepository<CouponDtlEntity, CouponList> {
    List<CouponDtlEntity> findByCpnCode(String cpnCode);
}