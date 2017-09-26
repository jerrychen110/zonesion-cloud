package com.zonesion.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.zonesion.cloud.domain.CourseMember;

@Repository
public interface CourseMemberRepository extends JpaRepository<CourseMember, Long>, JpaSpecificationExecutor<CourseMember> {

	CourseMember findOneByCourseIdAndUserId(Long courseId, Long UserId);
	
	@Query(value="select count(distinct user_id) from t_course_member where course_id = ?1",nativeQuery = true)
	int getLearnedNumByCourseId(long courseId);
	
	@Query(value="select count(distinct user_id) from t_course_member where course_id = ?1 and user_id = ?2",nativeQuery = true)
	int getLearnedNumByCourseIdAndUserId(Long courseId, Long userId);
}