package br.com.cursokotlin.pontointeligente.services.impl

import br.com.cursokotlin.pontointeligente.documents.Funcionario
import br.com.cursokotlin.pontointeligente.services.FuncionarioService
import org.springframework.stereotype.Service

@Service
class FuncionarioServiceImpl : FuncionarioService {
    override fun persistir(funcionario: Funcionario): Funcionario {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun buscarPorCpf(cpf: String): Funcionario? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun buscarPorEmail(email: String): Funcionario? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun buscarPorId(id: String): Funcionario? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}