package edu.api.bookflow.Services.patchHttpRequest;

import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class GlobalPatch {

    //metodo de reflexo para implementação da requisição http patch
    public static <T> void patch(T entidadeExistente, T entidadeIncompleta) throws IllegalAccessException {
        Class<?> entidadeClass = entidadeExistente.getClass();//instancia a classe compilada
        Field[] fieldsList = entidadeClass.getDeclaredFields();//retorna campos declarados na classe chamada

        for (Field field : fieldsList) { //varre todos os campos da classe
            field.getName(); //pega um campo de cada vez
            field.setAccessible(true); //e altera a proteção dos atributos para public

            Object value = field.get(entidadeIncompleta);

            if (value != null) {
                field.set(entidadeExistente, value); //altera valor do campo
            }
            field.setAccessible(false); //retorna atributos para private
        }
    }


}
