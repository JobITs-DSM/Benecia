package com.jobits.dsm.benecia.domain.enterprise.domain.businessarea;

import com.jobits.dsm.benecia.domain.enterprise.domain.Enterprise;
import org.springframework.data.repository.CrudRepository;

public interface BusinessAreaRepository extends CrudRepository<BusinessArea, Long> {
    void deleteAllByEnterprise(Enterprise enterprise);
}
