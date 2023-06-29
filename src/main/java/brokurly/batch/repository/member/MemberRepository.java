package brokurly.batch.repository.member;

import brokurly.batch.entity.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface MemberRepository extends JpaRepository<MemberEntity, String>, JpaSpecificationExecutor<MemberEntity> {
    List<MemberEntity> findByCustCode(String custCode); // 고객코드 조회조건으로 조회
}
