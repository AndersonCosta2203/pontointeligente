package br.com.cursokotlin.pontointeligente.documents

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/*
* - Esta Empresa irá representar um document no MongoDB,
*   - toda classe que representa o mongoDB, deve utilizar @Document
*   - toda classe que representar o mongo, deve utilizar o @Id para identificar o ID da classe no mongo
* - Definição dos atributos dentro do próprio construtor, definindo um construtor padrão
* - O Kotlin automaticamente irá criar os atributos, inclusive getter/setter
* - Tipo dos campos:
*   - var: é uma variável e pode ser modificado o seu valor
*   - val: é similar a uma constante, o seu estado não pode ser alterado
* - O operador ( ? ) diz o valor pode ser nulo, evita o null pointer exception
* - Operador ( data ) representa a crição dos acessores da empresa, semelhante aos getters e setters.
*   Deixar os campos disponíveis para acesso
* */

@Document
data class Empresa (
    val razaoSocial:String,
    val cnpj: String,
    @Id val id: String? = null
)