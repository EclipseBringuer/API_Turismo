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

/**
 * Servicio que gestiona las operaciones relacionadas con los usuarios.
 */
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    /**
     * Obtiene una lista de objetos InfoUsuarioDTO que contienen información resumida de todos los usuarios.
     *
     * @return Lista de InfoUsuarioDTO.
     */
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Obtiene un usuario por su token.
     *
     * @param token Token del usuario.
     * @return Usuario correspondiente al token o null si no se encuentra.
     */
    public List<InfoUsuarioDTO> getAllUsuarioDTO() {
        var output = usuarioRepository.findAll();
        return output.stream()
                .map(u -> new InfoUsuarioDTO(u.getName(), u.getGmail()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene un usuario por su token.
     *
     * @param token Token del usuario.
     * @return Usuario correspondiente al token o null si no se encuentra.
     */
    public Usuario getByToken(String token) {
        return usuarioRepository.getByToken(token);
    }

    /**
     * Obtiene el ID máximo de usuario.
     *
     * @return El ID máximo de usuario.
     */
    public Integer getMaxId() {
        return usuarioRepository.getMaxId();
    }

    /**
     * Registra un nuevo usuario con la información proporcionada y asigna un token.
     *
     * @param u     Datos del usuario a registrar.
     * @param token Token a asignar al usuario.
     * @return El usuario registrado.
     */
    public Usuario signUp(SignUpUserDTO u, String token) {
        var user = convertSignUpUserDTOToUser(u);
        user.setToken(token);
        return usuarioRepository.save(user);
    }

    /**
     * Inicia sesión con las credenciales proporcionadas y devuelve el token si la autenticación es exitosa.
     *
     * @param logInUserDTO Credenciales de inicio de sesión.
     * @return El token de usuario si la autenticación es exitosa; de lo contrario, un mensaje de error.
     */
    public String logIn(LogInUserDTO logInUserDTO) {
        var user = usuarioRepository.getUsuarioByGmailAndPass(logInUserDTO.gmail(), logInUserDTO.pass());
        String output = "Error al iniciar sesión";
        if (user != null) {
            output = user.getToken();
        }
        return output;
    }

    /**
     * Obtiene un objeto InfoUsuarioDTO con información resumida de un usuario por su ID.
     *
     * @param id ID del usuario.
     * @return InfoUsuarioDTO correspondiente al ID o null si no se encuentra.
     */
    public InfoUsuarioDTO getById(Integer id){
        InfoUsuarioDTO output = null;
        if(usuarioRepository.existsById(id)){
            output = convertUsuarioToInfoDTO(usuarioRepository.findById(id).get());
        }
        return output;
    }

    /**
     * Convierte un objeto Usuario a InfoUsuarioDTO para obtener una representación resumida del usuario.
     *
     * @param u Usuario a convertir.
     * @return InfoUsuarioDTO correspondiente al usuario.
     */
    private InfoUsuarioDTO convertUsuarioToInfoDTO(Usuario u){
        return new InfoUsuarioDTO(u.getName(), u.getGmail());
    }

    /**
     * Convierte un objeto SignUpUserDTO a Usuario para preparar los datos del usuario para el registro.
     *
     * @param upUserDTO Objeto SignUpUserDTO con datos del usuario para el registro.
     * @return Usuario correspondiente al objeto SignUpUserDTO.
     */
    private Usuario convertSignUpUserDTOToUser(SignUpUserDTO upUserDTO) {
        Usuario u = new Usuario();
        u.setName(upUserDTO.name());
        u.setGmail(upUserDTO.gmail());
        u.setPass(upUserDTO.pass());
        return u;
    }

    /**
     * Elimina un usuario por su token.
     *
     * @param token Token del usuario a eliminar.
     */
    public void deleteByToken(String token){
        usuarioRepository.deleteByToken(token);
    }
}
