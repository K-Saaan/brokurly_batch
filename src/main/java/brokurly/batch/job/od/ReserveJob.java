package brokurly.batch.job.od;

import brokurly.batch.common.DateUtil;
import brokurly.batch.entity.order.OrderEntity;
import brokurly.batch.entity.reserve.ReserveEntity;
import brokurly.batch.repository.order.OrderRepository;
import brokurly.batch.repository.reserve.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Configuration
public class ReserveJob {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final EntityManagerFactory entityManagerFactory;
    private final OrderRepository orderRepository;
    private final ReserveRepository reserveRepository;
    Timestamp now = new Timestamp(System.currentTimeMillis());
    private static final int chunkSize = 10;

    // 수행할 작업의 Job을 정의한다.
    @Bean
    public Job reserveProc() throws Exception {
        return jobBuilderFactory.get("reserveProc")
                .start(step1())
                .build();
    }

    @Bean
    @JobScope
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .tasklet((stepContribution, chunkContext) -> {
                    logger.info("step1 start >>>>>>>>");
                    // od_info에서 RESERVE_YN이 N인 건만 조회
                    List<OrderEntity> order = orderRepository.findReserveN();
                    logger.info("orderList : {}", order.size());
                    List<String> odList = new ArrayList<>();
                    for (int i=0; i<order.size(); i++){
                        logger.info("order " + order.get(i).getOdCode());
                        double reserveAmt = order.get(i).getTobeReserve();
                        logger.info("OdDate : " + order.get(i).getOdDate().substring(0, 8));
                        String odCustCode = order.get(i).getCustCode();
                        // 주문일자가 7일이상 지났을 경우 reserve_amt_info 테이블에 적립금 정보를 저장한다.
                        if (DateUtil.compareDay(order.get(i).getOdDate().substring(0, 8), DateUtil.getStringToday().substring(0, 8)) >= 7){
                            ReserveEntity reserveEntity = ReserveEntity.builder().custCode(odCustCode).reserveTyp("10")
                                    .reserveDt(DateUtil.getStringToday().substring(0, 8)).reserveAmt(reserveAmt).reserveDesc("")
                                    .regId("Admin").regDate(now.toLocalDateTime()).chgrId("").chgrDate(now.toLocalDateTime()).build();
                            reserveRepository.save(reserveEntity);
                            odList.add(order.get(i).getOdCode());
                        }
                    }
                    for (int i=0; i<odList.size(); i++){
                        logger.info("odList : " + odList.get(i));
                    }
                    // 적립금을 지급한 주문건이 있으면 해당 주문번호의 RESERVE_YN을 Y로 변경
                    if (odList.size() > 0){
                        orderRepository.updateReserveYn(odList);
                    }
                    logger.info("step1 end >>>>>>>>");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
