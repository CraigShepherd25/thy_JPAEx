package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.user;

public interface userRepository extends JpaRepository<user,Integer>{

}