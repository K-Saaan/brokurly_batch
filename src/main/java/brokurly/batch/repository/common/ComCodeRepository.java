package brokurly.batch.repository.common;

import brokurly.batch.dto.co.ComCodeListDto;
import brokurly.batch.entity.common.ComCodeEntity;
import brokurly.batch.entity.common.ComCodeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComCodeRepository extends JpaRepository<ComCodeEntity, ComCodeList> {

    @Query("select new brokurly.batch.dto.co.ComCodeListDto(c.comCdNm) from ComCodeEntity c where c.comCodeList.comCdGrpId = :comCdGrpId order by c.index")
    List<ComCodeListDto> findAllByComCdGrpId(String comCdGrpId);
}
