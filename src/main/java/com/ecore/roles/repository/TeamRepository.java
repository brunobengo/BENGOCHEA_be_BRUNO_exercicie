package com.ecore.roles.repository;

import com.ecore.roles.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TeamRepository extends JpaRepository<Team, UUID> {
}
