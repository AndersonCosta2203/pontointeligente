package br.com.cursokotlin.pontointeligente.repositories

import br.com.cursokotlin.pontointeligente.documents.Empresa
import org.springframework.data.mongodb.repository.MongoRepository
/*
* - ( : ) indica que estamos extendendo as Funcionalidades de MongoRepository (Interface)
* - Entre EmpresaRepository e MongoRepository há um espaço, é uma convenção
* */
interface EmpresaRepository : MongoRepository<Empresa, String> {

    fun findByCnpj(cnpj: String): Empresa
}