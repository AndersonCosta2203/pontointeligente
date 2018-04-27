package br.com.cursokotlin.pontointeligente

import br.com.cursokotlin.pontointeligente.documents.Empresa
import br.com.cursokotlin.pontointeligente.documents.Funcionario
import br.com.cursokotlin.pontointeligente.documents.Lancamento
import br.com.cursokotlin.pontointeligente.enums.PerfilEnum
import br.com.cursokotlin.pontointeligente.enums.TipoEnum
import br.com.cursokotlin.pontointeligente.repositories.EmpresaRepository
import br.com.cursokotlin.pontointeligente.repositories.FuncionarioRepository
import br.com.cursokotlin.pontointeligente.repositories.LancamentoRepository
import br.com.cursokotlin.pontointeligente.utils.SenhaUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

/**
 * @todo
 * O código abaixo serve somente para testes e deve ser removido ao término
 * do projeto, até porque ele faz alguns testes unitário falharem.
 *
 * Mantenha somente a declaração da classe e o método main, conforme criado no início do curso.
 *
 * Injetando empresa, funcionario e lancamento
 * !! operador utilizado para informar que o parametro é garantido nao ser null
 */

@SpringBootApplication
class PontointeligenteApplication(val empresaRepository: EmpresaRepository,
                                  val funcionarioRepository: FuncionarioRepository,
                                  val lancamentoRepository: LancamentoRepository) : CommandLineRunner {

    override fun run(vararg args: String?) {
        empresaRepository.deleteAll()
        funcionarioRepository.deleteAll()
        lancamentoRepository.deleteAll()

        val empresa: Empresa = Empresa("Empresa", "10443887000146")
        empresaRepository.save(empresa)

        val admin: Funcionario = Funcionario("Admin", "admin@empresa.com",
                SenhaUtils().gerarBcrypt("123456"), "25708317000",
                PerfilEnum.ROLE_ADMIN, empresa.id!!)
        funcionarioRepository.save(admin)

        val funcionario: Funcionario = Funcionario("Funcionario",
                "funcionario@empresa.com", SenhaUtils().gerarBcrypt("123456"),
                "44325441557", PerfilEnum.ROLE_USUARIO, empresa.id!!)
        funcionarioRepository.save(funcionario)

        val lancamento: Lancamento = Lancamento(Date(), TipoEnum.INICIO_TRABALHO,
                funcionario.id!!, "lancamento 1", "localizacao 1")
        lancamentoRepository.save(lancamento)

        System.out.println("Empresa ID: " + empresa.id)
        System.out.println("Admin ID: " + admin.id)
        System.out.println("Funcionario ID: " + funcionario.id)
        println("lancamento ID: "+ lancamento.id)

    }

}

fun main(args: Array<String>) {
    runApplication<PontointeligenteApplication>(*args)
}
