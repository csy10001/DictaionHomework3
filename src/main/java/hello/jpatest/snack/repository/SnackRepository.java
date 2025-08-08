package hello.jpatest.snack.repository;

import hello.jpatest.snack.entity.Snack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackRepository extends JpaRepository<Snack,Long>{
}
