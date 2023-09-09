package br.com.treinaweb.twprojetos.api.annotations;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "int", paramType = "query", defaultValue = "0", value = "Número da página que deseja recuperar (1..N)"),
        @ApiImplicitParam(name = "size", dataType = "int", paramType = "query", defaultValue = "2", value = "Número de elementos por página")
})
public @interface ApiPageable {
}
