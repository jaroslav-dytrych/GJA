package cz.vutbr.fit.gja.spring.boot.demo;

import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

}
