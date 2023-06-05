package com.ufps.webdificar.proyecto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ufps.webdificar.proyecto.entities.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, Integer> {

}
