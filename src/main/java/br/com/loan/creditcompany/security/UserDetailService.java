package br.com.loan.creditcompany.security;

import br.com.loan.creditcompany.model.entity.UserEntity;
import br.com.loan.creditcompany.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<UserEntity> byUsername = userRepository.findByUsername(username);

        if (byUsername.isPresent()){
            return byUsername.get();
        }

        throw new UsernameNotFoundException("Not found user with username: ".concat(username));
    }
}
