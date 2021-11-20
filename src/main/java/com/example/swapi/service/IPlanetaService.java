package com.example.swapi.service;

import java.util.Map;

public interface IPlanetaService<T> {
    T adicionar(T t);
    T buscarPorId(Integer id);
    Map<Integer,T> buscarPorNome(String nome);
    Map<Integer,T> listarTodos();
    boolean deletar(Integer id);
}
