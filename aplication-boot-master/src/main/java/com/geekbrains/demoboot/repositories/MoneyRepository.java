package com.geekbrains.demoboot.repositories;

import com.geekbrains.demoboot.entities.Money;
import com.geekbrains.demoboot.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyRepository extends JpaRepository<Money, Long> {
}
