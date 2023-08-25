package brokurly.batch.job.pd;

import brokurly.batch.common.DateUtil;
import brokurly.batch.entity.product.CouponEntity;
import brokurly.batch.entity.product.ReviewEntity;
import brokurly.batch.entity.reserve.ReserveEntity;
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
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Configuration
public class ReviewReserve {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;

    private static final int chunkSize = 100;

    // 수행할 작업의 Job을 정의한다.
    @Bean
    public Job reviewReserveJob() throws Exception {
        return jobBuilderFactory.get("reviewReserveJob")
                .start(reviewReserveStep())
                .build();
    }
    @Bean
    @JobScope // 쿠폰의 날짜가 현재 시간과 일치하면 상태값을 변경하는 작업 Reader -> processor -> writer 순으로 수행
    public Step reviewReserveStep() throws Exception {
        return stepBuilderFactory.get("reviewReserveStep")
                .<ReviewEntity, ReserveEntity>chunk(chunkSize) // ReviewEntity 타입으로 읽어서 ReviewEntity로 넘겨준다
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    @Bean // 조회에 사용할 params를 정의하고 조회한다.
    public JpaPagingItemReader<ReviewEntity> itemReader() throws Exception{
        logger.info("JpaPagingItemReader : start");
        Map<String, Object> params = new HashMap<>();
        params.put("reserveYn", "N");
        params.put("reviewDate", DateUtil.getTodayYYYYMM());
        return new JpaPagingItemReaderBuilder<ReviewEntity>()
                .name("jdbcPagingItemReader")
                .pageSize(chunkSize)
                .parameterValues(params)
                .entityManagerFactory(entityManagerFactory)
                .queryString("SELECT r\n" +
                        "\tFROM ReviewEntity r\n" +
                        "\tWHERE 1=1\n" +
                        "\tAND SUBSTR(r.reviewDate, 1, 6) = :reviewDate\n" +
                        "\tAND r.reserveYn = :reserveYn\n" +
                        "\tGROUP BY r.custCode, r.pdCode" )
                .build();
    }

    @Bean
    public ItemProcessor<ReviewEntity, ReserveEntity> itemProcessor() throws Exception {
        return new ItemProcessor<ReviewEntity, ReserveEntity>() {
            @Override // Reader에서 읽어온 coupon 정보를 받아서 처리한다
            public ReserveEntity process(ReviewEntity item) throws Exception {

                logger.info("item type : ", item.getClass().getName());
                logger.info("custCode : " + item.getCustCode());
                logger.info("reviewDate : " + item.getReviewDate());
                logger.info("reviewTyp : " + item.getReviewTyp());
                // 리뷰 유형에 따라 적립금 데이터 다르게 설정
                ReserveEntity reserveEntity = new ReserveEntity();
                if (item.getReviewTyp().equals("T")) {
                    reserveEntity.setReserveAmt(50.0);
                    reserveEntity.setReserveTyp("20");
                    reserveEntity.setReserveDesc("글 후기 적립");
                    // 적립 대상 SeqNo의 적립여부(reserveYn)을 Y로 update
                    item.updateReserveYn(item.getReviewSeqNo(), "Y");
                } else {
                    reserveEntity.setReserveAmt(100.0);
                    reserveEntity.setReserveTyp("30");
                    reserveEntity.setReserveDesc("사진 후기 적립");
                    // 적립 대상 SeqNo의 적립여부(reserveYn)을 Y로 update
                    item.updateReserveYn(item.getReviewSeqNo(), "Y");
                }

                Timestamp now = new Timestamp(System.currentTimeMillis());
                reserveEntity.setCustCode(item.getCustCode());
                reserveEntity.setReserveDt(DateUtil.getTodayYYYYMMDD());
                reserveEntity.setRegId(item.getRegId());
                reserveEntity.setRegDate(now.toLocalDateTime());

                return reserveEntity;
            }
        };
    }

    @Bean // Processor에서 값이 변경된 Entity를 저장한다.
    public ItemWriter<ReserveEntity> itemWriter() throws Exception{
        logger.info("JpaItemWriter : start");
        JpaItemWriter<ReserveEntity> jpaItemWriter = new JpaItemWriterBuilder<ReserveEntity>()
                .entityManagerFactory(entityManagerFactory)
                .build();
        jpaItemWriter.afterPropertiesSet();
        return jpaItemWriter;
    }
}
