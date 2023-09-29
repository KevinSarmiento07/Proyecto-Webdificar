package com.ufps.webdificar.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ufps.webdificar.proyecto.entities.Documento;
@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

}
