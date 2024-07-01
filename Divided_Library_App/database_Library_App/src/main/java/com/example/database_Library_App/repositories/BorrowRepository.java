package com.example.database_Library_App.repositories;


import com.example.database_Library_App.entities.Borrow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow,Long> {
}
