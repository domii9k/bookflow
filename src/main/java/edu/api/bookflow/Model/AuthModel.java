package edu.api.bookflow.Model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class AuthModel {

    private String email;
    private String senha;
}
