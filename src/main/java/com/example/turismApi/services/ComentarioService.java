package com.example.turismApi.services;

import com.example.turismApi.model.dto.comentario.InfoComentarioDTO;
import com.example.turismApi.model.dto.comentario.NewComentarioDTO;
import com.example.turismApi.model.entity.Comentario;
import com.example.turismApi.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Servicio que proporciona operaciones relacionadas con comentarios.
 */
@Service
public class ComentarioService {
    private final ComentarioRepository comentarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PuntoInteresService puntoInteresService;

    /**
     * Constructor del servicio ComentarioService.
     *
     * @param comentarioRepository Repositorio de comentarios.
     */
    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    /**
     * Obtiene todos los comentarios.
     *
     * @return Lista de InfoComentarioDTO.
     */
    public List<InfoComentarioDTO> getAll() {
        var output = comentarioRepository.findAll();
        return output.stream()
                .map(c -> new InfoComentarioDTO(c.getUsuario().getName(), c.getPoi().getName(), c.getContenido()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los comentarios realizados por un usuario específico.
     *
     * @param id ID del usuario.
     * @return Lista de InfoComentarioDTO realizados por el usuario.
     */
    public List<InfoComentarioDTO> getAllByUser(Integer id) {
        var output = comentarioRepository.getAllByUsuario_Id(id);
        return output.stream()
                .map(c -> new InfoComentarioDTO(c.getUsuario().getName(), c.getPoi().getName(), c.getContenido()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todos los comentarios asociados a un Punto de Interés (POI) específico.
     *
     * @param id ID del Punto de Interés (POI).
     * @return Lista de InfoComentarioDTO asociados al POI.
     */
    public List<InfoComentarioDTO> getAllByPoi(Integer id) {
        var output = comentarioRepository.getAllByPoi_Id(id);
        return output.stream()
                .map(c -> new InfoComentarioDTO(c.getUsuario().getName(), c.getPoi().getName(), c.getContenido()))
                .collect(Collectors.toList());
    }

    /**
     * Crea un nuevo comentario.
     *
     * @param token           Token de autenticación del usuario.
     * @param comentarioDTO   DTO con la información del nuevo comentario.
     * @return InfoComentarioDTO del comentario creado.
     */
    public InfoComentarioDTO createNewComentario(String token, NewComentarioDTO comentarioDTO) {
        InfoComentarioDTO output = null;
        var user = usuarioService.getByToken(token);
        var poi = puntoInteresService.getByIdNotDTO(comentarioDTO.id_poi());
        if (poi != null) {
            var comentario = new Comentario();
            comentario.setContenido(comentarioDTO.content());
            comentario.setPoi(poi);
            comentario.setUsuario(user);
            output = convertComentarioToInfoDTO(comentarioRepository.save(comentario));
        }
        return output;
    }

    /**
     * Elimina un comentario.
     *
     * @param id     ID del comentario a eliminar.
     * @param token  Token de autenticación del usuario.
     * @return Mensaje indicando el resultado de la operación.
     */
    public String deleteComentario(Integer id, String token) {
        String output = null;
        var user = usuarioService.getByToken(token);
        boolean found = false;
        for (Comentario c : user.getComentarios()) {
            if (Objects.equals(c.getId(), id)) {
                found = true;
                break;
            }
        }
        if (found) {
            comentarioRepository.deleteById(id);
            output = "Comentario borrado correctamente";
        }
        return output;
    }

    /**
     * Convierte un objeto Comentario a InfoComentarioDTO.
     *
     * @param c Comentario a convertir.
     * @return InfoComentarioDTO resultante.
     */
    private InfoComentarioDTO convertComentarioToInfoDTO(Comentario c) {
        return new InfoComentarioDTO(c.getUsuario().getName(), c.getPoi().getName(), c.getContenido());
    }

    /**
     * Obtiene un comentario por su ID.
     *
     * @param id ID del comentario.
     * @return InfoComentarioDTO del comentario encontrado.
     */
    public InfoComentarioDTO getComentarioById(Integer id) {
        InfoComentarioDTO output = null;
        if (comentarioRepository.existsById(id)) {
            output = convertComentarioToInfoDTO(comentarioRepository.findById(id).get());
        }
        return output;
    }
}
