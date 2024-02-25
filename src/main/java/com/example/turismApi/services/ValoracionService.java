package com.example.turismApi.services;

import com.example.turismApi.model.dto.valoracion.InfoValoracionDTO;
import com.example.turismApi.model.dto.valoracion.NewValoracionDTO;
import com.example.turismApi.model.entity.Valoracion;
import com.example.turismApi.repositories.ValoracionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ValoracionService {
    private final ValoracionRepository valoracionRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PuntoInteresService puntoInteresService;

    @Autowired
    public ValoracionService(ValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    public List<InfoValoracionDTO> getAll() {
        var output = valoracionRepository.findAll();
        return output.stream()
                .map(v -> new InfoValoracionDTO(v.getUsuario().getName(), v.getPoi().getName(), v.getPuntuacion()))
                .collect(Collectors.toList());
    }

    public List<InfoValoracionDTO> getAllByUser(Integer id) {
        var output = valoracionRepository.getAllByUsuario_Id(id);
        return output.stream()
                .map(v -> new InfoValoracionDTO(v.getUsuario().getName(), v.getPoi().getName(), v.getPuntuacion()))
                .collect(Collectors.toList());
    }

    public List<InfoValoracionDTO> getAllByPoi(Integer id) {
        var output = valoracionRepository.getAllByPoi_Id(id);
        return output.stream()
                .map(c -> new InfoValoracionDTO(c.getUsuario().getName(), c.getPoi().getName(), c.getPuntuacion()))
                .collect(Collectors.toList());
    }

    public InfoValoracionDTO createNewValoracion(String token, NewValoracionDTO valoracionDTO) {
        InfoValoracionDTO output = null;
        var user = usuarioService.getByToken(token);
        var poi = puntoInteresService.getById(valoracionDTO.id_poi());
        if (poi != null) {
            var comentario = new Valoracion();
            comentario.setPuntuacion(valoracionDTO.valoracion());
            comentario.setPoi(poi);
            comentario.setUsuario(user);
            output = convertValoracionToInfoDTO(valoracionRepository.save(comentario));
        }
        return output;
    }

    public String deleteValoracion(Integer id, String token) {
        String output = null;
        var user = usuarioService.getByToken(token);
        boolean found = false;
        for (Valoracion v : user.getValoraciones()) {
            if (Objects.equals(v.getId(), id)) {
                found = true;
                break;
            }
        }
        if (found) {
            valoracionRepository.deleteById(id);
            output = "Valoración borrada correctamente";
        }
        return output;
    }

    private InfoValoracionDTO convertValoracionToInfoDTO(Valoracion v) {
        return new InfoValoracionDTO(v.getUsuario().getName(), v.getPoi().getName(), v.getPuntuacion());
    }

    public InfoValoracionDTO getValoracionById(Integer id) {
        InfoValoracionDTO output = null;
        if (valoracionRepository.existsById(id)) {
            output = convertValoracionToInfoDTO(valoracionRepository.findById(id).get());
        }
        return output;
    }
}