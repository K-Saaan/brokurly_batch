package brokurly.batch.repository.product;

import brokurly.batch.entity.product.ProductDtlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductDtlRepository extends JpaRepository<ProductDtlEntity, String> {
	List<ProductDtlEntity> findByPdCode(String pdCode);
}
