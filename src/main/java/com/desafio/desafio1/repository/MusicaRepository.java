package com.desafio.desafio1.repository;

import com.desafio.desafio1.model.Musica;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MusicaRepository extends MongoRepository<Musica, String> {
    
}
