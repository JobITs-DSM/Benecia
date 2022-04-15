package com.jobits.dsm.benecia.domain.recruitment.domain.tag;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Integer> {
    List<Tag> findByNameContains(String keyword);
}
