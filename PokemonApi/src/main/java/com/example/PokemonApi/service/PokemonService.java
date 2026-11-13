package com.example.PokemonApi.service;

import com.example.PokemonApi.exception.ResourceNotFoundException;
import com.example.PokemonApi.model.Pokemon;
import com.example.PokemonApi.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> obtenerTodos() {
        return pokemonRepository.findAll();
    }

    public Pokemon obtenerPorId(Long id) {
        return pokemonRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró el Pokémon con ID: " + id
                ));
    }

    public Pokemon actualizarPokemonCompleto(Long id, Pokemon pokemonActualizado) {
        return pokemonRepository.findById(id).map(pokemon -> {
            pokemon.setNombre(pokemonActualizado.getNombre());
            pokemon.setTipo(pokemonActualizado.getTipo());
            pokemon.setNivel(pokemonActualizado.getNivel());
            pokemon.setAtaque(pokemonActualizado.getAtaque());
            pokemon.setDefensa(pokemonActualizado.getDefensa());
            pokemon.setDescripcion(pokemonActualizado.getDescripcion());
            pokemon.setImagenUrl(pokemonActualizado.getImagenUrl());
            pokemon.setEmail(pokemonActualizado.getEmail());

            return pokemonRepository.save(pokemon);
        }).orElse(null);
    }

    public Pokemon actualizarPokemonParcial(Long id, Pokemon pokemonParcial) {
        return pokemonRepository.findById(id).map(pokemon -> {

            if (pokemonParcial.getNombre() != null)
                pokemon.setNombre(pokemonParcial.getNombre());

            if (pokemonParcial.getTipo() != null)
                pokemon.setTipo(pokemonParcial.getTipo());

            if (pokemonParcial.getNivel() != null)
                pokemon.setNivel(pokemonParcial.getNivel());

            if (pokemonParcial.getAtaque() != null)
                pokemon.setAtaque(pokemonParcial.getAtaque());

            if (pokemonParcial.getDefensa() != null)
                pokemon.setDefensa(pokemonParcial.getDefensa());

            if (pokemonParcial.getDescripcion() != null)
                pokemon.setDescripcion(pokemonParcial.getDescripcion());

            if (pokemonParcial.getImagenUrl() != null)
                pokemon.setImagenUrl(pokemonParcial.getImagenUrl());

            if (pokemonParcial.getEmail() != null)
                pokemon.setEmail(pokemonParcial.getEmail());

            return pokemonRepository.save(pokemon);
        }).orElse(null);
    }

    public void eliminarPokemon(Long id) {
        pokemonRepository.deleteById(id);
    }
}