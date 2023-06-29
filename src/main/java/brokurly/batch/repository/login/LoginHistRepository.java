package brokurly.batch.repository.login;

import brokurly.batch.entity.login.LoginHistEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginHistRepository extends JpaRepository<LoginHistEntity, String>, JpaSpecificationExecutor<LoginHistEntity>{
	
}
