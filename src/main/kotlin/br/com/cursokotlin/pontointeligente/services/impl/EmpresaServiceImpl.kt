package br.com.cursokotlin.pontointeligente.services.impl

import br.com.cursokotlin.pontointeligente.documents.Empresa
import br.com.cursokotlin.pontointeligente.repositories.EmpresaRepository
import br.com.cursokotlin.pontointeligente.services.EmpresaService
import org.springframework.stereotype.Service

/*
* - Para identificar uma classe como service, deve-se informar a notação @Service
* - Após o nome da classe quando declaramos (val empresaRepository : ... ) estamos injetando empresaRepository
* - O Kotlin recomenda que não seja utilizado o @AutoWired
* - Devido a implementação do método ter apenas uma linha, após o ( = ) podemos definir em uma única linha
* */

@Service
class EmpresaServiceImpl(val empresaRepository: EmpresaRepository) : EmpresaService{

    override fun buscarPorCnpj(cnpj: String): Empresa? = empresaRepository.findByCnpj(cnpj)

    override fun persistir(empresa: Empresa): Empresa = empresaRepository.save(empresa)
}