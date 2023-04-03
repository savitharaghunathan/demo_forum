package com.konveyor.forum.demo.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ForumRepository extends CrudRepository<Forum, Long> {

	public List<Forum> findByName(String name);

}
