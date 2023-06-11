package com.ufps.webdificar.proyecto.service.interfaces;

import java.util.List;

import com.ufps.webdificar.proyecto.entities.Documento;

public interface DocumentoInterface {
	
	public List<Documento> getAllDocumentos();
	
	public Documento getById(Documento documento);
	
	public Documento create(Documento documento);
	
	
	public Documento update(Documento documento);
	
	public void deleteById(Integer id);
	
	
	
	

}
