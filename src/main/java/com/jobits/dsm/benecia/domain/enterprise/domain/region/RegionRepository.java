package com.jobits.dsm.benecia.domain.enterprise.domain.region;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<Region, Integer> {
    List<Region> findAllByNameContains(String keyword);
    Optional<Region> findByName(String name);
}
