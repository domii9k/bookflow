package edu.api.bookflow.Services.patchHttpRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

@Component
public class GlobalPatch {

    @Deprecated
    /*
    * Este método não permite que campos que nao foram preenchidos sejam nulos,
    * obrigando que, nas classes DTO, os atributos tenham que permitir valores nulos,
    * para, assim, realizar a atualização dos dados
    * */
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

    public static <T> void globalPatch(Map<String, Object> fields, T classe){
        ObjectMapper objectMapper = new ObjectMapper();
        T classeConverte = objectMapper.convertValue(fields, (Class<T>) classe.getClass());
        fields.forEach((chave, valor)-> {
            Field field = ReflectionUtils.findField(classe.getClass(), chave);
            field.setAccessible(true);

            Object novoObjeto =  ReflectionUtils.getField(field, classeConverte);

            ReflectionUtils.setField(field, classe, novoObjeto);

            field.setAccessible(false);
        });
    }

}
