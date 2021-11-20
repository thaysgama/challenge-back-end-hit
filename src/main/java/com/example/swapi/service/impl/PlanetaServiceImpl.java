package com.example.swapi.service.impl;

import com.example.swapi.model.Planeta;
import com.example.swapi.repository.impl.PlanetaRepositoryImpl;
import com.example.swapi.service.IPlanetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PlanetaServiceImpl implements IPlanetaService<Planeta> {

    @Autowired
    private PlanetaRepositoryImpl planetaRepository;

    @Override
    public Planeta adicionar(Planeta planeta) {
        return planetaRepository.adicionar(planeta);
    }

    @Override
    public Planeta buscarPorId(Integer id) {
        return planetaRepository.buscarPorId(id);
    }

    @Override
    public Map<Integer, Planeta> buscarPorNome(String nome) {
        return planetaRepository.buscarPorNome(nome);
    }

    @Override
    public Map<Integer, Planeta> listarTodos() {
        return planetaRepository.listarTodos();
    }

    @Override
    public boolean deletar(Integer id) {
        return planetaRepository.deletar(id);
    }
}
