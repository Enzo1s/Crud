package com.DisneyWorld.PeliculaSerie.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DisneyWorld.PeliculaSerie.Model.Entity.PeliculaSerie;

@Repository
public interface PeliculaSerieRepository extends JpaRepository<PeliculaSerie, Long>{

	public Iterable<PeliculaSerie> findByTitulo(String titulo);
}
