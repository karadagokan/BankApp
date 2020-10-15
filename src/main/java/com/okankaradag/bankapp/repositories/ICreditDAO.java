package com.okankaradag.bankapp.repositories;
import com.okankaradag.bankapp.models.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICreditDAO extends JpaRepository<Credit,Integer> {

}
