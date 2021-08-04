package ru.zsa.repeat_spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zsa.repeat_spring.model.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
