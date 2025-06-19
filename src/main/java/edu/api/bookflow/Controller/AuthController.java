package edu.api.bookflow.Controller;

import edu.api.bookflow.Configuration.AuthConfig.TokenProvider;
import edu.api.bookflow.DTO.AuthDTO;
import edu.api.bookflow.DTO.LoginResponseDTO;
import edu.api.bookflow.DTO.Mapper.LoginMapper;
import edu.api.bookflow.DTO.Mapper.UsuarioMapper;
import edu.api.bookflow.DTO.UsuarioDTO;
import edu.api.bookflow.Model.AuthModel;
import edu.api.bookflow.Model.Usuario;
import edu.api.bookflow.Repository.UsuarioRepository;
import edu.api.bookflow.Services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthController {
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    private UsuarioMapper usuarioMapper;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthDTO dto) {
        var emailSenha = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var auth = this.authenticationManager.authenticate(emailSenha);
        var token = tokenProvider.generateAccessToken((Usuario) auth.getPrincipal());
        AuthModel model = loginMapper.convertToModel(dto);
        Usuario usuario = (Usuario) usuarioRepository.findByEmail(model.getEmail());
        UsuarioDTO usuarioDTO = usuarioMapper.convertToDto(usuario);

        return ResponseEntity.ok(new LoginResponseDTO(token, usuarioDTO));
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/registro")
    public ResponseEntity<Object> registro(@RequestBody @Valid UsuarioDTO dto) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.senha());

        Usuario novoUsuario = new Usuario();

        novoUsuario.setNome(dto.nome());
        novoUsuario.setSobrenome(dto.sobrenome());
        novoUsuario.setSenha(encryptedPassword);
        novoUsuario.setPermissao(dto.permissao());
        novoUsuario.setCpf(dto.cpf());
        novoUsuario.setEmail(dto.email());

        try {
            usuarioService.validaCPFeEmail(novoUsuario);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        this.usuarioRepository.save(novoUsuario);

        return ResponseEntity.ok().body("Usuário criado com sucesso!");
    }
}
