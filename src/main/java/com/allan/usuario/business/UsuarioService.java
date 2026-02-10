package com.allan.usuario.business;

import com.allan.usuario.business.converter.UsuarioConverter;
import com.allan.usuario.business.dto.EnderecoDTO;
import com.allan.usuario.business.dto.TelefoneDTO;
import com.allan.usuario.business.dto.UsuarioDTO;
import com.allan.usuario.infrastructure.entity.Endereco;
import com.allan.usuario.infrastructure.entity.Telefone;
import com.allan.usuario.infrastructure.entity.Usuario;
import com.allan.usuario.infrastructure.excptions.ResourceNotFoundException;
import com.allan.usuario.infrastructure.repository.EnderecoRepository;
import com.allan.usuario.infrastructure.repository.TelefoneRepository;
import com.allan.usuario.infrastructure.repository.UsuarioRepository;
import com.allan.usuario.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final EnderecoRepository enderecoRepository;
    private final TelefoneRepository telefoneRepository;


    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));

        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);

        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }


    public UsuarioDTO buscarUsuarioPorEmail(String email) {
        try {
            return usuarioConverter.paraUsuarioDTO(
                    usuarioRepository.findByEmail(email)
                            .orElseThrow(
                                    () -> new ResourceNotFoundException(
                                            "Usuário não encontrado com o email: " + email
                                    )));
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Email nao encontrado: " + email);
        }

    }

    public void deletarUsuarioPorEmail(String email) {
        usuarioRepository.deleteByEmail(email);
    }


    public UsuarioDTO atualizaDadosUsuario(String token, UsuarioDTO dto) {
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        //criptografa a senha caso ela seja diferente de null, caso contrário, mantém o valor atual da senha
        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null);

        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email nao localizado: " + email));

        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);
        return usuarioConverter.paraUsuarioDTO(usuarioRepository.save(usuario));
    }

    public EnderecoDTO atualizaEndereco(Long idEndereco, EnderecoDTO enderecoDTO) {

        Endereco entity = enderecoRepository.findById(idEndereco).orElseThrow(() ->
                new ResourceNotFoundException("Id nao encontrado: " + idEndereco));

        Endereco endereco = usuarioConverter.updateEndereco(enderecoDTO, entity);

        return usuarioConverter.paraEnderecoDTO(enderecoRepository.save(endereco));
    }

    public TelefoneDTO atualizaTelefone(Long idTelefone, TelefoneDTO dto) {
        Telefone entity = telefoneRepository.findById(idTelefone).orElseThrow(() ->
                new ResourceNotFoundException("Id nao encontrado: " + idTelefone));

        Telefone telefone = usuarioConverter.updateTelefone(dto, entity);

        return usuarioConverter.paraTelefoneDTO(telefoneRepository.save(telefone));
    }

}
