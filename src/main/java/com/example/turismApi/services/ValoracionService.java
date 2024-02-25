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

/**
 * Servicio que gestiona las operaciones relacionadas con las valoraciones.
 */
@Service
public class ValoracionService {
    private final ValoracionRepository valoracionRepository;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private PuntoInteresService puntoInteresService;

    /**
     * Constructor del servicio.
     *
     * @param valoracionRepository Repositorio de las valoraciones.
     */
    @Autowired
    public ValoracionService(ValoracionRepository valoracionRepository) {
        this.valoracionRepository = valoracionRepository;
    }

    /**
     * Obtiene todas las valoraciones disponibles.
     *
     * @return Lista de InfoValoracionDTO que contienen información sobre las valoraciones.
     */
    public List<InfoValoracionDTO> getAll() {
        var output = valoracionRepository.findAll();
        return output.stream()
                .map(v -> new InfoValoracionDTO(v.getUsuario().getName(), v.getPoi().getName(), v.getPuntuacion()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las valoraciones realizadas por un usuario específico.
     *
     * @param id Identificador del usuario.
     * @return Lista de InfoValoracionDTO que contienen información sobre las valoraciones realizadas por el usuario.
     */
    public List<InfoValoracionDTO> getAllByUser(Integer id) {
        var output = valoracionRepository.getAllByUsuario_Id(id);
        return output.stream()
                .map(v -> new InfoValoracionDTO(v.getUsuario().getName(), v.getPoi().getName(), v.getPuntuacion()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las valoraciones asociadas a un punto de interés específico.
     *
     * @param id Identificador del punto de interés.
     * @return Lista de InfoValoracionDTO que contienen información sobre las valoraciones asociadas al punto de interés.
     */
    public List<InfoValoracionDTO> getAllByPoi(Integer id) {
        var output = valoracionRepository.getAllByPoi_Id(id);
        return output.stream()
                .map(c -> new InfoValoracionDTO(c.getUsuario().getName(), c.getPoi().getName(), c.getPuntuacion()))
                .collect(Collectors.toList());
    }

    /**
     * Crea una nueva valoración y la asocia al usuario y al punto de interés correspondientes.
     *
     * @param token           Token de autenticación del usuario.
     * @param valoracionDTO   DTO con la información de la nueva valoración.
     * @return InfoValoracionDTO que contiene información sobre la valoración creada.
     */
    public InfoValoracionDTO createNewValoracion(String token, NewValoracionDTO valoracionDTO) {
        InfoValoracionDTO output = null;
        var user = usuarioService.getByToken(token);
        var poi = puntoInteresService.getByIdNotDTO(valoracionDTO.id_poi());
        if (poi != null) {
            var comentario = new Valoracion();
            comentario.setPuntuacion(valoracionDTO.valoracion());
            comentario.setPoi(poi);
            comentario.setUsuario(user);
            output = convertValoracionToInfoDTO(valoracionRepository.save(comentario));
        }
        return output;
    }

    /**
     * Elimina una valoración específica realizada por un usuario.
     *
     * @param id     Identificador de la valoración.
     * @param token  Token de autenticación del usuario.
     * @return Mensaje indicando si la eliminación fue exitosa.
     */
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

    /**
     * Convierte una entidad de Valoración a su representación DTO.
     *
     * @param valoracion Valoración a convertir.
     * @return InfoValoracionDTO que contiene información sobre la valoración.
     */
    private InfoValoracionDTO convertValoracionToInfoDTO(Valoracion v) {
        return new InfoValoracionDTO(v.getUsuario().getName(), v.getPoi().getName(), v.getPuntuacion());
    }

    /**
     * Obtiene una valoración específica por su identificador.
     *
     * @param id Identificador de la valoración.
     * @return InfoValoracionDTO que contiene información sobre la valoración.
     */
    public InfoValoracionDTO getValoracionById(Integer id) {
        InfoValoracionDTO output = null;
        if (valoracionRepository.existsById(id)) {
            output = convertValoracionToInfoDTO(valoracionRepository.findById(id).get());
        }
        return output;
    }
}