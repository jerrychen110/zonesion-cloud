package com.zonesion.cloud.repository;

import com.zonesion.cloud.domain.Major;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Major entity.
 */
@Repository
public interface MajorRepository extends JpaRepository<Major,Long> {
    
	@Query(value="select * from t_major order by id",nativeQuery = true)
	List<Major> findAllOrderById();
}
