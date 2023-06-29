package brokurly.batch.repository.etc;

import brokurly.batch.entity.etc.NoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NoticeRepository extends JpaRepository<NoticeEntity, Integer>, JpaSpecificationExecutor<NoticeEntity> {

}
