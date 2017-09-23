package com.zonesion.cloud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.zonesion.cloud.domain.CourseMember;

@Repository
public interface CourseMemberRepository extends JpaRepository<CourseMember, Long>, JpaSpecificationExecutor<CourseMember> {

	CourseMember findOneByCourseIdAndUserId(Long courseId, Long UserId);
}