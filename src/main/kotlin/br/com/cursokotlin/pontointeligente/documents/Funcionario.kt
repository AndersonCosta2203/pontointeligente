package br.com.cursokotlin.pontointeligente.documents

import br.com.cursokotlin.pontointeligente.enums.PerfilEnum
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class Funcionario (
        val nome: String,
        val email: String,
        val senha: String,
        val cpf: String,
        val perfil: PerfilEnum,
        val empresaId: String,
        val valorHora: Double? = 0.0,
        val qtdHorasTrabalhoDia: Float? = 0.0f,
        val qtdHorasAlmoco: Float? = 0.0f,
        @Id val id: String? = null
)