package br.com.cursokotlin.pontointeligente.services

import br.com.cursokotlin.pontointeligente.documents.Empresa

/*
* - Não é todo cnpj que irá retornar uma empresa, por isso colocar ( ? ) para que possa retornar null,
*   quando a empresa não for encontrada
* */

interface EmpresaService{

    fun buscarPorCnpj(cnpj: String): Empresa?

    fun persistir(empresa: Empresa): Empresa
}