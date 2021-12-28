package com.jobits.dsm.benecia.domain.enterprise.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, String>, EnterpriseRepositoryCustom {
}
