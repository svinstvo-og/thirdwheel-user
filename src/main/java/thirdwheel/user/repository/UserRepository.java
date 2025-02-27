package thirdwheel.user.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import thirdwheel.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    public User findByuId(Long uId)
            throws EmptyResultDataAccessException;
    public User findByEmail(String email);
        //throws EmptyResultDataAccessException;
}
