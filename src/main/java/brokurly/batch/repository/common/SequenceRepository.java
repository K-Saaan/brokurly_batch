package brokurly.batch.repository.common;

import brokurly.batch.entity.common.SequenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SequenceRepository extends JpaRepository<SequenceEntity, String>, JpaSpecificationExecutor<SequenceEntity> {
    SequenceEntity findByTblCode(String tblCode);
}
