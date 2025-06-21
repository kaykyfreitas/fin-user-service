package dev.kaykyfreitas.finuserservice.finuserservice.adapter.outbound.jpa.repository;

import dev.kaykyfreitas.finuserservice.finuserservice.adapter.outbound.jpa.entity.UserJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, String> {
}
