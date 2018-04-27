package br.com.cursokotlin.pontointeligente.services.impl

import br.com.cursokotlin.pontointeligente.documents.Funcionario
import br.com.cursokotlin.pontointeligente.repositories.FuncionarioRepository
import br.com.cursokotlin.pontointeligente.services.FuncionarioService
import org.springframework.stereotype.Service
import java.util.*
/*
* A partir da versão 2 do Spring é obrigatório utilizar o Optional<> do java 8 para evitar null pointer
* o método default findOne() foi substituido por findById()
* */
@Service
class FuncionarioServiceImpl(val funcionarioRepository: FuncionarioRepository) : FuncionarioService {
    override fun persistir(funcionario: Funcionario): Funcionario = funcionarioRepository.save(funcionario)

    override fun buscarPorCpf(cpf: String): Funcionario? = funcionarioRepository.findByCpf(cpf)

    override fun buscarPorEmail(email: String): Funcionario? = funcionarioRepository.findByEmail(email)

    override fun buscarPorId(id: String): Optional<Funcionario>? = funcionarioRepository.findById(id)

}