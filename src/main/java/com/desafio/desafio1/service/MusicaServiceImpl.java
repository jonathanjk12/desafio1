package com.desafio.desafio1.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.desafio.desafio1.model.Musica;
import com.desafio.desafio1.repository.MusicaRepository;
import com.desafio.desafio1.shared.MusicaDTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class MusicaServiceImpl implements MusicaService {
    
    @Autowired
    MusicaRepository repositorioMusica;

    @Override
    public List<MusicaDTO> obterTodos() {
        
        List<Musica> musicas = repositorioMusica.findAll();
        
        ModelMapper mapper = new ModelMapper();
        
        return musicas.stream()
        .map(musica ->mapper.map(musica, MusicaDTO.class))
        .collect(Collectors.toList());
    }

    @Override
    public Optional<MusicaDTO> obterPorId(String idMusica) {
        
        Optional <Musica> optionalMusica = repositorioMusica.findById(idMusica);
        
        if(optionalMusica.isEmpty()){
            throw new InputMismatchException("Musica não pode ser encontrada através do ID: " + idMusica);
        }
        
        MusicaDTO musicaDto = new ModelMapper().map(optionalMusica.get(), MusicaDTO.class);
        return Optional.of(musicaDto);
    }

    @Override
    public MusicaDTO adicionar(MusicaDTO musicaDto) {
        
        ModelMapper mapper = new ModelMapper();
        
        Musica musica = mapper.map(musicaDto, Musica.class);
        
        musica.setId(null);
        
        musica = repositorioMusica.save(musica);
        return mapper.map(musica, MusicaDTO.class);
    }

    @Override
    public MusicaDTO atualizar(String idMusica, MusicaDTO musicaDto) {
       
        musicaDto.setId(idMusica);
       
        Musica musica = new ModelMapper().map(musicaDto, Musica.class);
       
        repositorioMusica.save(musica);
       return musicaDto;
    }

    @Override
    public void deletar (String idMusica) {
        
        repositorioMusica.deleteById(idMusica);
        
    }
    
}
