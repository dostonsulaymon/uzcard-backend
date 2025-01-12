//package dasturlash.uz.config;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.ProviderManager;
//
//@Configuration
//public class AuthenticationConfig {
//
//
//
//    @Bean
//    @Qualifier("profileAuthenticationManager")
//    public AuthenticationManager profileAuthenticationManager() {
//        return new ProviderManager(profileAuthenticationProvider());
//    }
//
//    @Bean
//    @Qualifier("companyAuthenticationManager")
//    public AuthenticationManager companyAuthenticationManager() {
//        return new ProviderManager(companyAuthenticationProvider());
//    }
//}