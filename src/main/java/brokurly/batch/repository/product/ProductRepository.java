package brokurly.batch.repository.product;

import brokurly.batch.dto.product.ProductReviewDto;
import brokurly.batch.entity.product.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, String>, JpaSpecificationExecutor<ProductEntity> {
	@Query(value = "SELECT SALES_UNIT, COUNT(SALES_UNIT) as CNT, SUM(PD_PRICE) as SUM from pd.pd_info group by SALES_UNIT", nativeQuery=true)
	List<Object[]> showBySalesUnit();
	
	// 상품 카테고리별 상품가 총합
	@Query(value = "SELECT c.CATE_NM, sum(p.PD_PRICE) as sum FROM pd.pd_info p, pd.pd_cate c where p.PD_CODE = c.PD_CODE group by c.CATE_NM", nativeQuery=true)
	List<Object[]> showSumOfProduct();

	// 상품, 리뷰 테이블 조인 조회. 상품코드, 상품이름, 리뷰내용만 조회.
	@Query("select new brokurly.batch.dto.product.ProductReviewDto(p.pdCode, p.pdNm, r.reviewTxt) from ProductEntity p join ReviewEntity r on p.pdCode = r.pdCode")
//	List<ProductReviewDto> showProductAndReview();
	Page<ProductReviewDto> showProductAndReview(Pageable pageable);

}
