package edu.api.bookflow.DTO.Mapper;

import edu.api.bookflow.DTO.AuthDTO;
import edu.api.bookflow.Model.AuthModel;
import edu.api.bookflow.Model.Usuario;
import org.springframework.stereotype.Component;

@Component
public class LoginMapper {


    public AuthModel convertToModel(AuthDTO dto) {
        if (dto == null) {
            return null;
        }

        AuthModel authModel = new AuthModel();

        authModel.setEmail(dto.email());
        return authModel;


    }
}
