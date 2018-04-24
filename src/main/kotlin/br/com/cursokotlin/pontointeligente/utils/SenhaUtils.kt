package br.com.cursokotlin.pontointeligente.utils

import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

/*
* - Quando a implementacao possui apenas uma linha, o Kotlin permite que façamos a implementação inline
* - Não é necessário utlizar o new, apenas (), para criar uma instancia
* - Não é necessário colocar o return após o ( = )
* */

class SenhaUtils {

    fun gerarBcrypt(senha: String): String = BCryptPasswordEncoder().encode(senha)
}