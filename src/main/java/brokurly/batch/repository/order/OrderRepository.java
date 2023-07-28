package brokurly.batch.repository.order;

import brokurly.batch.entity.order.OrderEntity;
import brokurly.batch.entity.product.CouponEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String>, JpaSpecificationExecutor<OrderEntity> {
    @Query(value = "SELECT *  FROM od.od_info WHERE RESERVE_YN = 'N'", nativeQuery=true)
    List<OrderEntity> findReserveN();

    @Transactional
    @Modifying
    @Query(value = "UPDATE od.od_info SET RESERVE_YN = 'Y' WHERE OD_CODE IN (:odCode)", nativeQuery = true)
    int updateReserveYn(List<String> odCode);
}
