package com.warehausesystem.app.warehauseSystemWeb.repository;

import com.warehausesystem.app.warehauseSystemWeb.model.Compartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompartmentRepository extends JpaRepository<Compartment,Long> {
}
