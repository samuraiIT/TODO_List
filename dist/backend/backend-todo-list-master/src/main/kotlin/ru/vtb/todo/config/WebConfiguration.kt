package ru.vtb.todo.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod.*
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfiguration : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods(
                GET.name,
                POST.name,
                DELETE.name,
                OPTIONS.name
            )
    }
}