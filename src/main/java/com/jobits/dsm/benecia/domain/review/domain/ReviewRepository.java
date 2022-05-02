package com.jobits.dsm.benecia.domain.review.domain;

import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import com.jobits.dsm.benecia.domain.review.code.ReviewCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByEnterprise(Enterprise enterprise);
}
