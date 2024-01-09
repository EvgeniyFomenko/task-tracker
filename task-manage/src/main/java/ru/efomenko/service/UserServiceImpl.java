package ru.efomenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.efomenko.dto.RegistrationUserDto;
import ru.efomenko.dto.UserDto;
import ru.efomenko.entity.User;
import ru.efomenko.exception.UserNotFoundException;
import ru.efomenko.mapper.UserMapper;
import ru.efomenko.repository.RoleRepository;
import ru.efomenko.repository.UserRepository;
import ru.efomenko.utils.JwtPassEncoded;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtPassEncoded passEncoded;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, @Lazy JwtPassEncoded passEncoded) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passEncoded = passEncoded;
    }

    @Override
    public UserDto findUser(String login) {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(String.format("User with login %s not found", login)));

        return UserDto.builder().id(user.getId()).email(user.getEmail()).username(user.getUsername()).blocked(user.isBlocked()).build();
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException(String.format("User with login %s not found", login)));
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                user.getRoleList().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
    }

    @Override
    public UserDto createUser(RegistrationUserDto userDto) {
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            throw new UserNotFoundException("Пароли не совпадают");
        }

        if (userRepository.findByLogin(userDto.getLogin()).isPresent()) {
            throw new UserNotFoundException("пользователь уже существует");
        }

        User user = UserMapper.fromRegUser(userDto);
        user.setUsername(user.getLogin());

        user.setPassword(passEncoded.encoded(user.getPassword()));
        user.setRoleList(List.of(roleRepository.findByName("ROLE_USER").get()));
        return UserMapper.fromUser(userRepository.save(user));
    }

    @Override
    public void deleteUser(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("пользователь уже существует"));
        userRepository.delete(user);
    }

    @Override
    public UserDto findUserById(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("пользователь уже существует"));

        return UserMapper.fromUser(user);
    }

    @Override
    public List<UserDto> findUsers() {
        return userRepository.findAll().stream().map(UserMapper::fromUser).collect(Collectors.toList());
    }


}
