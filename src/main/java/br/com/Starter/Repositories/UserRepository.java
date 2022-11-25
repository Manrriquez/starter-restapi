package br.com.Starter.Repositories;


import br.com.Starter.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query(value = "select u from UserModel u where u.name like %?1%")
    List<UserModel> findName(String name);
}
