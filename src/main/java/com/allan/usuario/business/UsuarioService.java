package com.allan.usuario.business;

import com.allan.usuario.business.converter.UsuarioConverter;
import com.allan.usuario.business.dto.UsuarioDTO;
import com.allan.usuario.infrastructure.entity.Usuario;
import com.allan.usuario.infrastructure.exception.ConflictException;
import com.allan.usuario.infrastructure.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return  usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario));
    }

    public boolean verificaEmailExistente(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void emailExiste(String email){
        try{
            boolean exite = verificaEmailExistente(email);
            if(exite){
                throw new ConflictException("Email ja exitente: " + email);
            }
        }catch (ConflictException e){
            throw new ConflictException("Email ja cadastrado:" + e.getCause());
        }
    }
}
