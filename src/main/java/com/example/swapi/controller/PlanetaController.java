package com.example.swapi.controller;


import com.example.swapi.dto.PlanetaResponseDto;

import com.example.swapi.model.Planeta;
import com.example.swapi.service.impl.PlanetaServiceImpl;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController //junção da @Controller com a @ResponseBody
@RequestMapping("/api/v1")
public class PlanetaController {

    @Autowired
    private PlanetaServiceImpl planetaService;

    //deixei esse metodo mas creio que ele não deve existir. Foi só para testar se as responses estavam funcionando.
    //a API ta sendo puxada pelo PlanetaRepositoryImpl pegando só com as informações dos planetas
    @GetMapping
    public List<PlanetaResponseDto> listarPaginas()  {
        List<PlanetaResponseDto> listaPaginas = new ArrayList<>();
        for (int pagina = 1; pagina <= 6; pagina++) {
            PlanetaResponseDto planeta= Unirest.get("https://swapi.dev/api/planets/?page="+pagina)
                    .asObject(PlanetaResponseDto.class).getBody();
            listaPaginas.add(planeta);
        }
        return listaPaginas;
    }

    @GetMapping("/listarPlanetas")
    public ResponseEntity<Map<Integer, Planeta>> listarPlanetas(){
        return ResponseEntity.ok(planetaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Planeta> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(planetaService.buscarPorId(id));
    }

    @GetMapping("/nome={nome}")
    public ResponseEntity<Map<Integer, Planeta>> buscarPorNome(@PathVariable String nome){
        return ResponseEntity.ok(planetaService.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<Planeta> adicionar(@RequestBody Planeta planeta){
        return ResponseEntity.ok(planetaService.adicionar(planeta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id){
        boolean deletado = planetaService.deletar(id);
        if(deletado)
            return ResponseEntity.ok().body("Planeta deletado");
        return ResponseEntity.notFound().build();
    }

}
