package brokurly.batch.job.co;

import brokurly.batch.common.DateUtil;
import brokurly.batch.entity.product.CouponEntity;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class CouponStat {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private static final int chunkSize = 10;

    // 수행할 작업의 Job을 정의한다.
    @Bean
    public Job statUpdate() throws Exception {
        return jobBuilderFactory.get("statUpdate")
                .start(couponItemreaderStep())
                .build();
    }
    @Bean
    @JobScope // 쿠폰의 날짜가 현재 시간과 일치하면 상태값을 변경하는 작업 Reader -> processor -> writer 순으로 수행
    public Step couponItemreaderStep() throws Exception {
        return stepBuilderFactory.get("couponItemreaderStep")
                .<CouponEntity, CouponEntity>chunk(chunkSize) // CouponEntity 타입으로 읽어서 CouponEntity로 넘겨준다
                .reader(couponItemReader())
                .processor(couponItemProcessor())
                .writer(couponItemWriter())
                .build();
    }

    @Bean // 조회에 사용할 params를 정의하고 조회한다.
    public JpaPagingItemReader<CouponEntity> couponItemReader() throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("cpnStat", "10");
        logger.info("JpaPagingItemReader : start");
        return new JpaPagingItemReaderBuilder<CouponEntity>()
                .name("jdbcPagingItemReader")
                .pageSize(chunkSize)
                .parameterValues(params)
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT c FROM CouponEntity c WHERE c.cpnStat = :cpnStat")
                .build();
    }

    @Bean
    public ItemProcessor<CouponEntity, CouponEntity> couponItemProcessor() throws Exception{
        return new ItemProcessor<CouponEntity, CouponEntity>() {
            @Override // Reader에서 읽어온 coupon 정보를 받아서 처리한다
            public CouponEntity process(CouponEntity item) throws Exception {
                logger.info("cpnCode : " + item.getCpnCode());
                logger.info("cpnNm : " + item.getCpnNm());
                logger.info("cpnStat : " + item.getCpnStat());
                // 쿠폰의 시작일시가 현재와 같다면 20으로 상태값을 변경해서 저장한다
                if (DateUtil.compareDate(item.getStartDate().substring(0, 12), DateUtil.getStringToday().substring(0, 12))) {
                    item.setCpnStat("20");
                }
                return item;
            }
        };
    }

    @Bean // Processor에서 값이 변경된 Entity를 저장한다.
    public ItemWriter<CouponEntity> couponItemWriter() throws Exception{
        logger.info("JpaItemWriter : start");
        JpaItemWriter<CouponEntity> jpaItemWriter = new JpaItemWriterBuilder<CouponEntity>()
                .entityManagerFactory(entityManagerFactory)
                .build();
        jpaItemWriter.afterPropertiesSet();
        return jpaItemWriter;
    }

//    @Bean
//    public PagingQueryProvider createQueryProvider() throws Exception{
//        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
//        queryProvider.setSelectClause("cpnCode, cpnNm");
//        queryProvider.setFromClause("from couponEntity");
//        queryProvider.setWhereClause("where cpnCode = :cpnCODE");
//
//        return queryProvider.getObject();
//    }
}
