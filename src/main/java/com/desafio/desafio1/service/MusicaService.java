package com.desafio.desafio1.service;

import java.util.List;
import java.util.Optional;



import com.desafio.desafio1.shared.MusicaDTO;

public interface MusicaService {
    
    List<MusicaDTO> obterTodos();

    Optional<MusicaDTO> obterPorId(String idMusica);

    MusicaDTO adicionar (MusicaDTO musicaDto);

    MusicaDTO atualizar (String idMusica, MusicaDTO musicaDto);

    void deletar (String id);

}
