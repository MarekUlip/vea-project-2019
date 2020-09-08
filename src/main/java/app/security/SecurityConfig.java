package app.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import app.businesslayer.UserService;
import app.dto.AbstractUser;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserService userService;
	
	@Autowired
	public void init(AuthenticationManagerBuilder builder) throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories
				.createDelegatingPasswordEncoder();
		builder.inMemoryAuthentication()
			.withUser("marek@gmail.com").password(encoder.encode("aa"))
				.roles("ADMIN");
		builder.authenticationProvider(new AuthenticationProvider() {
			
			@Override
			public boolean supports(Class<?> authentication) {
				return authentication.equals(UsernamePasswordAuthenticationToken.class);
			}
			
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String name = authentication.getName();
				String password = authentication.getCredentials() != null 
						? authentication.getCredentials().toString():null;
				System.out.println(name);
				System.out.println(password);
				AbstractUser user = userService.findByEmail(name);
				if(user==null) {
					System.out.println("User is null");
					throw new AuthenticationServiceException("Wrong user name or password");
				}
				if(password.equals(user.getPassword())) {
					List<GrantedAuthority> authorities = new ArrayList<>();
					authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
					return new UsernamePasswordAuthenticationToken(name, password, authorities);
				}
				throw new AuthenticationServiceException("Wrong user name or password");
			}
		});
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		 http
		 .authorizeRequests()
         .antMatchers("/fragments/**", "/registration", "/new-user","/save-user","/resources/**","/css/**","/rest/reservations").permitAll()
         .anyRequest().authenticated().and()
         .formLogin().loginPage("/login").permitAll().and()
         .logout().permitAll().invalidateHttpSession(true).logoutSuccessUrl("/login?logout");
		//http.authorizeRequests().antMatchers("/**").permitAll().and().formLogin().loginPage("/login").permitAll();
		/*http.authorizeRequests().antMatchers("/**").authenticated().and()
			.authorizeRequests().antMatchers("/fragments/**").permitAll().and()
			.authorizeRequests().antMatchers("/index").permitAll().and()
			.authorizeRequests().antMatchers("/registration").permitAll().and()
			.formLogin().loginPage("/login").permitAll().and()
			.authorizeRequests().antMatchers("/rest/**").permitAll().and()
			.logout().permitAll().invalidateHttpSession(true).logoutSuccessUrl("/login?logout");*/
			//.authorizeRequests().antMatchers("/registration").permitAll().and()
			//.exceptionHandling().accessDeniedPage("/403");
	}

	
}

