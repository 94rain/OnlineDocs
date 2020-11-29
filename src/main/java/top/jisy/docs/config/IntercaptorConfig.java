// package top.jisy.docs.config;
//
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
// import top.jisy.docs.inteceptor.JWTInterceptor;
//
// @Configuration
// public class IntercaptorConfig implements WebMvcConfigurer {
//
//     @Override
//     public void addInterceptors(InterceptorRegistry registry) {
//         registry.addInterceptor(new JWTInterceptor())
//                 // intercepted path
//                 .addPathPatterns("/**")
//                 // excluding login interface
//                 .excludePathPatterns("/user/login");
//     }
// }