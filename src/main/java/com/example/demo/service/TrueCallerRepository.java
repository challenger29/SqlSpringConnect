package com.example.demo.service;

import com.example.demo.entity.TrueCallerRequestToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrueCallerRepository extends CrudRepository<TrueCallerRequestToken, Long> {
}
