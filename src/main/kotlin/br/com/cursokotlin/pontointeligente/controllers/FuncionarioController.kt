package br.com.cursokotlin.pontointeligente.controllers

import br.com.cursokotlin.pontointeligente.documents.Funcionario
import br.com.cursokotlin.pontointeligente.dtos.FuncionarioDto
import br.com.cursokotlin.pontointeligente.response.Response
import br.com.cursokotlin.pontointeligente.services.FuncionarioService
import br.com.cursokotlin.pontointeligente.utils.SenhaUtils
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.ObjectError
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/funcionarios")
class FuncionarioController(val funcionarioService: FuncionarioService) {

    @PutMapping(value = "/{id}")
    fun atualizar(@PathVariable("id") id: String, @Valid @RequestBody funcionarioDto: FuncionarioDto,
                  result: BindingResult): ResponseEntity<Response<FuncionarioDto>> {

        val response: Response<FuncionarioDto> = Response<FuncionarioDto>()
        val funcionario: Optional<Funcionario>? = funcionarioService.buscarPorId(id)

        if (funcionario == null) {
            result.addError(ObjectError("funcionario", "Funcionário não encontrado."))
        }

        if (result.hasErrors()) {
            for (erro in result.allErrors) response.erros.add(erro.defaultMessage!!)
            return ResponseEntity.badRequest().body(response)
        }

        val funcAtualizar: Funcionario = atualizarDadosFuncionario(funcionario!!, funcionarioDto)
        funcionarioService.persistir(funcAtualizar)
        response.data = converterFuncionarioDto(funcAtualizar)

        return ResponseEntity.ok(response)
    }

    private fun atualizarDadosFuncionario(funcionario: Optional<Funcionario>,
                                          funcionarioDto: FuncionarioDto): Funcionario {
        var senha: String
        var newFuncionario = funcionario.get()
        if (funcionarioDto.senha == null) {
            senha = newFuncionario.senha
        } else {
            senha = SenhaUtils().gerarBcrypt(funcionarioDto.senha)
        }

        return Funcionario(funcionarioDto.nome, newFuncionario.email, senha,
                newFuncionario.cpf, newFuncionario.perfil, newFuncionario.empresaId,
                funcionarioDto.valorHora?.toDouble(),
                funcionarioDto.qtdHorasTrabalhoDia?.toFloat(),
                funcionarioDto.qtdHorasAlmoco?.toFloat(),
                newFuncionario.id)
    }

    private fun converterFuncionarioDto(funcionario: Funcionario): FuncionarioDto =
            FuncionarioDto(funcionario.nome, funcionario.email, "",
                    funcionario.valorHora.toString(), funcionario.qtdHorasTrabalhoDia.toString(),
                    funcionario.qtdHorasAlmoco.toString(), funcionario.id)

}