package com.DisneyWorld.PeliculaSerie.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.DisneyWorld.PeliculaSerie.Model.Entity.PeliculaSerie;
import com.DisneyWorld.PeliculaSerie.Model.Repository.PeliculaSerieRepository;

@Service
public class PeliculaSerieServiceImpl implements PeliculaSerieService {
	
	@Autowired
	private PeliculaSerieRepository repository;

	@Override
	@Transactional(readOnly = true)
	public Iterable<PeliculaSerie> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<PeliculaSerie> finById(Long id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public PeliculaSerie save(PeliculaSerie peliculaSerie) {
		return repository.save(peliculaSerie);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {
		repository.deleteById(id);

	}

	@Override
	@Transactional(readOnly = true)
	public Iterable<PeliculaSerie> findByTitle(String title) {
		return repository.findByTitulo(title);
	}

}
