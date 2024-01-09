package ru.efomenko.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.efomenko.entity.File;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
}
