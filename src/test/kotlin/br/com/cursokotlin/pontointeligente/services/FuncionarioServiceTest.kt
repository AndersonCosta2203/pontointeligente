package br.com.cursokotlin.pontointeligente.services

import br.com.cursokotlin.pontointeligente.documents.Funcionario
import br.com.cursokotlin.pontointeligente.enums.PerfilEnum
import br.com.cursokotlin.pontointeligente.repositories.FuncionarioRepository
import br.com.cursokotlin.pontointeligente.utils.SenhaUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner
import java.util.*

/*
* Esta classe de teste foi utilizada baseando nos teste TDD
* Criar a classe de teste do que deseja testar gerando erros, e gerar a implementação até estar com o resultado correto
* Deve ser realizado um teste por vez, gerar um teste para cada funcionalidade
* 1º Como não implementamos ainda o método persistir da classe FuncionarioServiceImpl, o teste irá falhar
*   FuncionarioServiceImpl.persistir -> kotlin.NotImplementedError: An operation is not implemented: not implemented
* 2º
*
* */

@RunWith(SpringRunner::class)
@SpringBootTest
class FuncionarioServiceTest {

    @MockBean
    private val funcionarioRepository: FuncionarioRepository? = null

    @Autowired
    private val funcionarioService: FuncionarioService? = null

    private val email: String = "email@email.com"
    private val cpf: String = "34234855948"
    private val id: String = "1"

    @Before
    @Throws(Exception::class)
    fun setUp() {
        /* Mockito.any(Funcionario::class.java)), diz que quando chamar o save com qualquer objeto funcionario,
            deve ser retornado um funcionário
         */
        BDDMockito.given(funcionarioRepository?.save(Mockito.any(Funcionario::class.java)))
                .willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findById(id)).willReturn(funcionarioId())
        BDDMockito.given(funcionarioRepository?.findByEmail(email)).willReturn(funcionario())
        BDDMockito.given(funcionarioRepository?.findByCpf(cpf)).willReturn(funcionario())
    }

    @Test
    fun testPersistirFuncionario() {
        val funcionario: Funcionario? = this.funcionarioService?.persistir(funcionario())
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorId() {
        val funcionario: Optional<Funcionario>? = this.funcionarioService?.buscarPorId(id)
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorEmail() {
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorEmail(email)
        Assert.assertNotNull(funcionario)
    }

    @Test
    fun testBuscarFuncionarioPorCpf() {
        val funcionario: Funcionario? = this.funcionarioService?.buscarPorCpf(cpf)
        Assert.assertNotNull(funcionario)
    }

    private fun funcionario(): Funcionario =
            Funcionario("Nome", email, SenhaUtils().gerarBcrypt("123456"),
                    cpf, PerfilEnum.ROLE_USUARIO, id)

    private fun funcionarioId(): Optional<Funcionario> =
            Optional.of(Funcionario("Nome", email, SenhaUtils().gerarBcrypt("123456"),
                    cpf, PerfilEnum.ROLE_USUARIO, id))



}