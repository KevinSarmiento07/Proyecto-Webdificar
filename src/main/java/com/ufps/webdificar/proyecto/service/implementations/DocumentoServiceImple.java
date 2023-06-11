package com.ufps.webdificar.proyecto.service.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ufps.webdificar.proyecto.entities.Documento;
import com.ufps.webdificar.proyecto.repositories.DocumentoRepository;
import com.ufps.webdificar.proyecto.service.interfaces.DocumentoInterface;

@Service
public class DocumentoServiceImple implements DocumentoInterface {
	
	@Autowired
	DocumentoRepository documentoRepository;

	@Override
	public List<Documento> getAllDocumentos() {
		return documentoRepository.findAll();
	}

	@Override
	public Documento getById(Documento documento) {
		return documentoRepository.findById(documento.getId()).orElse(null);
	}

	@Override
	public Documento create(Documento documento) {
		return documentoRepository.save(documento);
	}

	@Override
	public Documento update(Documento documento) {
		return documentoRepository.save(documento);
	}

	@Override
	public void deleteById(Integer id) {
		documentoRepository.deleteById(id);
	}

}
