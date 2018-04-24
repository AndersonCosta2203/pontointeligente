package br.com.cursokotlin.pontointeligente.repositories

import br.com.cursokotlin.pontointeligente.documents.Funcionario
import org.springframework.data.mongodb.repository.MongoRepository

interface FuncionarioRepository : MongoRepository<Funcionario, String> {

    fun findByEmail(email: String): Funcionario

    fun findByCpf(cnpj: String): Funcionario
}