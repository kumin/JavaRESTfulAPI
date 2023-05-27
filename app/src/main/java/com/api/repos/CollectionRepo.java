package com.api.repos;

import com.api.entities.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionRepo extends JpaRepository<Collection, Long> {
}
