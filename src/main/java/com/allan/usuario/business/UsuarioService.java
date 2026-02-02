package com.allan.usuario.business;

import com.allan.usuario.business.converter.UsuarioConverter;
import com.allan.usuario.business.dto.UsuarioDTO;
import com.allan.usuario.infrastructure.entity.Usuario;
import com.allan.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return  usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario));
    }
}
