package com.example.turismApi.services;

import com.example.turismApi.model.dto.user.InfoUsuarioDTO;
import com.example.turismApi.model.dto.user.LogInUserDTO;
import com.example.turismApi.model.dto.user.SignUpUserDTO;
import com.example.turismApi.model.entity.Usuario;
import com.example.turismApi.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<InfoUsuarioDTO> getAllUsuarioDTO() {
        var output = usuarioRepository.findAll();
        return output.stream()
                .map(u -> new InfoUsuarioDTO(u.getName(), u.getGmail()))
                .collect(Collectors.toList());
    }

    public Usuario getByToken(String token) {
        return usuarioRepository.getByToken(token);
    }

    public Integer getMaxId() {
        return usuarioRepository.getMaxId();
    }

    public Usuario signUp(SignUpUserDTO u, String token) {
        var user = convertSignUpUserDTOToUser(u);
        user.setToken(token);
        return usuarioRepository.save(user);
    }

    public String logIn(LogInUserDTO logInUserDTO) {
        var user = usuarioRepository.getUsuarioByGmailAndPass(logInUserDTO.gmail(), logInUserDTO.pass());
        String output = "Error al iniciar sesión";
        if (user != null) {
            output = user.getToken();
        }
        return output;
    }

    private Usuario convertSignUpUserDTOToUser(SignUpUserDTO upUserDTO) {
        Usuario u = new Usuario();
        u.setName(upUserDTO.name());
        u.setGmail(upUserDTO.gmail());
        u.setPass(upUserDTO.pass());
        return u;
    }

}
