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

@Service
public class ComentarioService {
    private final ComentarioRepository comentarioRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PuntoInteresService puntoInteresService;

    @Autowired
    public ComentarioService(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

    public List<InfoComentarioDTO> getAll() {
        var output = comentarioRepository.findAll();
        return output.stream()
                .map(c -> new InfoComentarioDTO(c.getUsuario().getName(), c.getPoi().getName(), c.getContenido()))
                .collect(Collectors.toList());
    }

    public List<InfoComentarioDTO> getAllByUser(Integer id) {
        var output = comentarioRepository.getAllByUsuario_Id(id);
        return output.stream()
                .map(c -> new InfoComentarioDTO(c.getUsuario().getName(), c.getPoi().getName(), c.getContenido()))
                .collect(Collectors.toList());
    }

    public List<InfoComentarioDTO> getAllByPoi(Integer id) {
        var output = comentarioRepository.getAllByPoi_Id(id);
        return output.stream()
                .map(c -> new InfoComentarioDTO(c.getUsuario().getName(), c.getPoi().getName(), c.getContenido()))
                .collect(Collectors.toList());
    }

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

    private InfoComentarioDTO convertComentarioToInfoDTO(Comentario c) {
        return new InfoComentarioDTO(c.getUsuario().getName(), c.getPoi().getName(), c.getContenido());
    }

    public InfoComentarioDTO getComentarioById(Integer id) {
        InfoComentarioDTO output = null;
        if (comentarioRepository.existsById(id)) {
            output = convertComentarioToInfoDTO(comentarioRepository.findById(id).get());
        }
        return output;
    }
}
