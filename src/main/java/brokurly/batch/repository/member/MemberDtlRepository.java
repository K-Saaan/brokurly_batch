package brokurly.batch.repository.member;

import brokurly.batch.entity.member.MemberDtlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberDtlRepository extends JpaRepository<MemberDtlEntity, String> {
	List<MemberDtlEntity> findByCustCode(String custCode);
}
