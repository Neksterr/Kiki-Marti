package project.Kiki_Marti.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import project.Kiki_Marti.user.entity.UserEntity;

import java.lang.ScopedValue;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> loadUserByUsername(String email);
   Optional<UserEntity> findById(Long id);

   Optional<UserEntity> findByEmail(String email);

   Optional<UserEntity> findByUsername(String username);

   List<UserEntity> findTop10ByUsernameContainingIgnoreCase(String username);

   boolean existsByEmail(String email);

   boolean existsByUsername(String username);
}
