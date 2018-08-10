package br.com.cursokotlin.pontointeligente.security

import br.com.cursokotlin.pontointeligente.documents.Funcionario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * out -> garantir que receba apenas filhos de GrantedAuthority
 */
class FuncionarioPrincipal(val funcionario: Funcionario) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities: MutableCollection<GrantedAuthority> = mutableListOf<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority(funcionario.perfil.toString()))
        return authorities
    }

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = funcionario.email

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = funcionario.senha

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

}