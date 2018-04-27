package com.kazale.pontointeligente.dtos
/*
* Classe do tipo data para processar aos documents
* */
data class EmpresaDto (
        val razaoSocial: String,
        val cnpj: String,
        val id: String? = null
)
