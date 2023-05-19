package com.ecore.roles.repository;

import com.ecore.roles.model.Membership;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, UUID> {

    List<Membership> findByUserIdAndTeamId(UUID userId, UUID teamId);
}
