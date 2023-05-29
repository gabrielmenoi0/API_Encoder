package com.urlEncoder.builder.repository;

import com.urlEncoder.builder.domain.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository("UrlRepository")
public interface UrlRepository extends JpaRepository<Url, UUID> {
    public Url findByhash(String hash);
    public Url findByurl(String url);
}
