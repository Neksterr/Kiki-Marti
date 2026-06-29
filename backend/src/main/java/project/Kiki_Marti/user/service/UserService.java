package project.Kiki_Marti.user.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.Kiki_Marti.user.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }
}
