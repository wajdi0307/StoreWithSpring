package com.tekup.examen.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tekup.examen.entities.FournisseurDTO;

@Repository
public interface FournisseurRepository extends JpaRepository<FournisseurDTO, Long> {

}
