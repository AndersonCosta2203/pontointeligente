package br.com.cursokotlin.pontointeligente.services

import br.com.cursokotlin.pontointeligente.documents.Lancamento
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.util.*

/*
* No método remover não é necessário adicionar o void, pois como não colocamos o retorno, o Kotlin sabe que é void
* */
interface LancamentoService {

    fun buscarPorFuncionarioId(funcionarioId: String, pageRequest: PageRequest): Page<Lancamento>

    fun buscarPorId(id: String): Optional<Lancamento>?

    fun persistir(lancamento: Lancamento): Lancamento

    fun remover(id: String)
}