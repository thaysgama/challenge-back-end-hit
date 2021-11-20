package com.example.swapi.repository;

import java.util.Map;

public interface IRepository<T> {
    T adicionar(T t);
    T buscarPorId(Integer id);
    Map<Integer,T> buscarPorNome(String nome);
    Map<Integer,T> listarTodos();
    boolean deletar(Integer id);
}
