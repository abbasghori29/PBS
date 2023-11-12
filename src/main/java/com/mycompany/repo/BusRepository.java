package com.mycompany.repo;

import com.mycompany.models.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus,Integer> {
}
