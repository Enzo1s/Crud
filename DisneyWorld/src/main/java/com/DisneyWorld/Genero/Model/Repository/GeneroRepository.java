package com.DisneyWorld.Genero.Model.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.DisneyWorld.Genero.Model.Entity.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Long>{

}
