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
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class EndCouponUpdate {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final DataSource dataSource;

    private static final int chunkSize = 10;

    // 수행할 작업의 Job을 정의한다.
    @Bean
    public Job endCouponStatUpdate() throws Exception {
        return jobBuilderFactory.get("endCouponStatUpdate")
                .start(endCouponItemreaderStep())
                .build();
    }
    @Bean
    @JobScope // 쿠폰의 날짜가 현재 시간과 일치하면 상태값을 변경하는 작업 Reader -> processor -> writer 순으로 수행
    public Step endCouponItemreaderStep() throws Exception {
        return stepBuilderFactory.get("endCouponItemreaderStep")
                .<CouponEntity, CouponEntity>chunk(chunkSize) // CouponEntity 타입으로 읽어서 CouponEntity로 넘겨준다
                .reader(endCouponItemReader())
                .processor(endCouponItemProcessor())
                .writer(compositeItemWriter())
                .build();
    }

    @Bean // 조회에 사용할 params를 정의하고 조회한다.
    public JpaPagingItemReader<CouponEntity> endCouponItemReader() throws Exception{
        Map<String, Object> params = new HashMap<>();
        params.put("cpnStat", "50");
        logger.info("JpaPagingItemReader : start");
        return new JpaPagingItemReaderBuilder<CouponEntity>()
                .name("jdbcPagingItemReader")
                .pageSize(chunkSize)
                .parameterValues(params)
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT c FROM CouponEntity c WHERE c.cpnStat != :cpnStat")
                .build();
    }

    @Bean
    public ItemProcessor<CouponEntity, CouponEntity> endCouponItemProcessor() throws Exception{
        return new ItemProcessor<CouponEntity, CouponEntity>() {
            @Override // Reader에서 읽어온 coupon 정보를 받아서 처리한다
            public CouponEntity process(CouponEntity item) throws Exception {
                logger.info("cpnCode : " + item.getCpnCode());
                logger.info("cpnNm : " + item.getCpnNm());
                logger.info("cpnStat : " + item.getCpnStat());
                // 쿠폰의 종료일시가 현재거나 지났다면 만료로 값을 수정한다.
                if (DateUtil.beforeDate(item.getEndDate().substring(0, 12), DateUtil.getStringToday().substring(0, 12))) {
                    item.setCpnStat("50");
                }
                return item;
            }
        };
    }

    @Bean
    public CompositeItemWriter compositeItemWriter() throws Exception{
        List<ItemWriter> writers = new ArrayList<>();
        writers.add(couponTblWriter());
        writers.add(couponHistTblWriter());

        CompositeItemWriter itemWriter = new CompositeItemWriter();
        itemWriter.setDelegates(writers);
        return itemWriter;
    }

    public ItemWriter<CouponEntity> couponTblWriter() throws Exception{
        JpaItemWriter<CouponEntity> jpaItemWriter = new JpaItemWriterBuilder<CouponEntity>()
                .entityManagerFactory(entityManagerFactory)
                .build();
        jpaItemWriter.afterPropertiesSet();
        return jpaItemWriter;
    }

    public ItemWriter<CouponEntity> couponHistTblWriter() throws Exception{
        JdbcBatchItemWriter<CouponEntity> jdbcBatchItemWriter = new JdbcBatchItemWriterBuilder<CouponEntity>()
                .dataSource(dataSource)
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .assertUpdates(false)
                .sql("UPDATE pd.cpn_hist SET CPN_STAT = '50' WHERE CPN_CODE =:cpnCode")
                .build();
        jdbcBatchItemWriter.afterPropertiesSet();
        return jdbcBatchItemWriter;
    }
}
