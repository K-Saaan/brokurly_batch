package brokurly.batch.repository.order;

import brokurly.batch.entity.order.OrderDtlEntity;
import brokurly.batch.entity.product.OrderPdList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDtlRepository extends JpaRepository<OrderDtlEntity, OrderPdList> {

}
