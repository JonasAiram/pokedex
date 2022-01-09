package com.br.pokedexv1.commons

import java.lang.Exception

class InvalidNetworking(val code: Int = 1004, message: String) :
    Exception("$message\nCoding: $code")

class PokemonInvalidException(
    val code: Int = 1001,
    message: String = "Dados dos pokemons estão vazios.\nCódigo: $code"
) :
    Exception(message)

class NotFoundNetWorking(
    val code: Int = 1002,
    message: String = "Sem conexão com a internet.\nCódigo: $code"
) : Exception(message)
