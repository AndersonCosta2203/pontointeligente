package br.com.cursokotlin.pontointeligente.services

import br.com.cursokotlin.pontointeligente.documents.Empresa
import br.com.cursokotlin.pontointeligente.repositories.EmpresaRepository
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit4.SpringRunner

/*
* @MockBean -> Para criar um objeto falso que irá simular o acesso ao repository
* Visto que a empresaRepository foi inicializada com null, na função setup, após a declaração de
*   empresaRepository, também devemos utilizar ( ? )
* Outro papel do ( ? ) é, caso a empresaRepository seja null, não será executado o findByCnpj
* O Kotlin realizada uma verificação e para cada lugar que possa gerado um null pointer, ele obriga a inserir o ( ? )
* @Before é executado antes de cada teste, método usado para configuração, por convenção chamado ade setup
* Ao executar esta classe, pode demorar um pouco, pois é criado um contexto para o Spring rodar os testes
* Para realizar os testes, ele utiliza o o.s.b.a.mongo.embedded.EmbeddedMongo, não está executando a instância real do mongo
* */

@RunWith(SpringRunner::class)
@SpringBootTest
class EmpresaServiceTest {

    @Autowired
    val empresaService: EmpresaService? = null

    @MockBean
    private val empresaRepository: EmpresaRepository? = null

    private val CNPJ = "51463645000100"

    @Before
    @Throws(Exception::class)
    fun setup() {
        // Para cada chamada a empresaRepository.findByCnpj, retorne a empresa()
        BDDMockito.given(empresaRepository?.findByCnpj(CNPJ)).willReturn(empresa())
        BDDMockito.given(empresaRepository?.save(empresa())).willReturn(empresa())
    }

    @Test
    fun testBuscarEmpresaPorCnpj() {
        val empresa: Empresa? = empresaService?.buscarPorCnpj(CNPJ)
        Assert.assertNotNull(empresa)
    }

    @Test
    fun testPersistirEmpresa() {
        val empresa: Empresa? = empresaService?.persistir(empresa())
        Assert.assertNotNull(empresa)
    }

    private fun empresa(): Empresa = Empresa("Razao Social", CNPJ, "1")
}