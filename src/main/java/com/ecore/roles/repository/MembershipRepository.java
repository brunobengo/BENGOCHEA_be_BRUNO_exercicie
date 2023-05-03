package com.ecore.roles.repository;

import com.ecore.roles.model.Membership;
import com.ecore.roles.web.dto.*;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MembershipRepository extends JpaRepository<Membership, UUID> {

    List<Membership> findByUserIdAndTeamId(UUID userId, UUID teamId);
}
