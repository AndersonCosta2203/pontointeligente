package br.com.cursokotlin.pontointeligente.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

/**
 * @Configuration -> carrega quando o Spring é inicializado
 * @EnableWebSecurity -> Habilitando Web Security na aplicação
 * @EnableGlobalMethodSecurity -> Para validar específico por perfil
 * ? -> Após o parametro, informa que é opcional o parametro
 * @Bean -> irá tornar o método inicializável, injeção de dependência
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig(
        val funcionarioDetailsService: FuncionarioDetailsService) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(authenticationProvider())
    }

    /**
     * authorizeRequests -> será utilizado autorização nas requisições
     * anyRequest -> será autenticado todas as requisições
     * authenticated -> garantir que todas as requisições estejam autenticadas
     * httpBasic -> será utilizado autenticação básica
     * sessionManagement -> será utilizada autenticação por sessão
     * sessionCreationPolicy (SessionCreationPolicy.STATELESS) -> será criada uma sessão do tipo STATELESS que será válida durante a requisição
     * csrf (disable) -> utilizado quando há multiplas requisições em um sessão, neste caso estará desabilitada
     */
    override fun configure(http: HttpSecurity?) {
        http?.authorizeRequests()?.
                anyRequest()?.
                authenticated()?.and()?.
                httpBasic()?.and()?.
                sessionManagement()?.
                sessionCreationPolicy(SessionCreationPolicy.STATELESS)?.and()?.
                csrf()?.disable()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(funcionarioDetailsService)
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }

    @Bean
    fun encoder(): PasswordEncoder = BCryptPasswordEncoder()

}