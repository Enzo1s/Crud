package com.DisneyWorld.PeliculaSerie.Service;

import java.util.Optional;

import com.DisneyWorld.PeliculaSerie.Model.Entity.PeliculaSerie;

public interface PeliculaSerieService {

	public Iterable<PeliculaSerie> findAll();
	
	public Optional<PeliculaSerie> finById(Long id);
	
	public PeliculaSerie save(PeliculaSerie peliculaSerie);
	
	public void deleteById(Long id);
	
	public Iterable<PeliculaSerie> findByTitle(String title);
	
}
