package com.example.swapi.repository.impl;

import com.example.swapi.dto.PlanetaDto;
import com.example.swapi.dto.PlanetaResponseDto;
import com.example.swapi.model.Planeta;
import com.example.swapi.repository.IRepository;
import kong.unirest.Unirest;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class PlanetaRepositoryImpl implements IRepository<Planeta> {
    private static Map<Integer, PlanetaDto> planetaDTOMap = new HashMap<>();
    private static Integer counterId = 1;

    public PlanetaRepositoryImpl() {
        planetaDTOMap = buscarAPI();
    }

    @Override
    public Planeta adicionar(Planeta planeta) {
        planeta.setId(counterId);
        PlanetaDto planetaDto = new PlanetaDto(planeta);
        planetaDTOMap.put(counterId, planetaDto);
        counterId++;
        return planeta;
    }

    @Override
    public Planeta buscarPorId(Integer id) {
        return  new Planeta(planetaDTOMap.get(id));
    }

    @Override
    public Map<Integer, Planeta> buscarPorNome(String nome) {
        Map<Integer, Planeta> planetaMap = planetaDTOMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().getName().toLowerCase().contains(nome.toLowerCase()))
                .collect(Collectors.toMap(x -> x.getKey(), x -> new Planeta(x.getValue())));
        return planetaMap;
    }

    @Override
    public Map<Integer, Planeta> listarTodos() {
        Map<Integer, Planeta> planetaMap  = new HashMap<>();

        for(var item : planetaDTOMap.entrySet())
            planetaMap.put(item.getKey(), new Planeta(item.getValue()));

        return planetaMap;
    }

    @Override
    public boolean deletar(Integer id) {
        if(buscarPorId(id)!=null) {
            planetaDTOMap.remove(id);
            return true;
        }
        return false;
    }

    public Map<Integer, PlanetaDto> buscarAPI(){
        //pegando todos as paginas de dados da API
        for (int pagina = 1; pagina <= 6; pagina++) {
            PlanetaResponseDto planetaResoponse = Unirest.get("https://swapi.dev/api/planets/?page="+pagina)
                    .asObject(PlanetaResponseDto.class).getBody();

            //colocando um id para todos os planetas
            for(PlanetaDto planetaDto : planetaResoponse.getResults()){
                planetaDto.setId(counterId);
                counterId++;

                //preenchendo o map somente com as info dos planetas
                planetaDTOMap.put(planetaDto.getId(), planetaDto);
            }
        }
        return planetaDTOMap;
    }
}
