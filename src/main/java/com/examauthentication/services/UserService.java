package com.examauthentication.services;

import com.examauthentication.dtos.user.UserCreateDto;
import com.examauthentication.infrastructure.exception.GlobalException;
import com.examauthentication.models.User;
import com.examauthentication.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.examauthentication.utils.ModelMapperService;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {
    private final ModelMapperService modelMapperService;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserCreateDto userCreateDto) {
        validations(userCreateDto);
        var user = modelMapperService.toObject(User.class, userCreateDto);
        encodePasswordUser(user);
        userRepository.save(user);
    }

    private void encodePasswordUser(User user) {
        var encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
    }

    private void validations(UserCreateDto userCreateDto) {
        existUser(userCreateDto);
    }

    private void existUser(UserCreateDto userCreateDto) {
        if (userRepository.existsUserByEmail(userCreateDto.getEmail()))
            throw GlobalException.builder()
                    .msg(String.format("User with email: %s already registered in the system.", userCreateDto.getEmail()))
                    .build();

        if (userRepository.existsUserByCpf(userCreateDto.getCpf()))
            throw GlobalException.builder()
                    .msg(String.format("User with cpf: %s already registered in the system.", userCreateDto.getCpf()))
                    .build();
    }


}
