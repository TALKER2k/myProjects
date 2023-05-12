package com.geekbrains.demoboot.services;

import com.geekbrains.demoboot.entities.Money;
import com.geekbrains.demoboot.repositories.MoneyRepository;
import com.geekbrains.demoboot.repositories.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Component
@Service
public class MoneysService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UsersService usersService;
    @Autowired
    MoneyRepository moneyRepository;

    public boolean transactMoney(Long id, double amount, String In, String Out){
        List<Money> money = moneyRepository.findAll();
        if (In.equals(Out)) {
            return true;
        }
        if (In.equals("RUB") && Out.equals("USD")) {
            if (usersService.findUserById(id).getMoneyRUB() < amount)
                return false;
            usersService.findUserById(id).setMoneyRUB(usersService.findUserById(id).getMoneyRUB() - amount);
            usersService.findUserById(id).setMoneyUSD(usersService.findUserById(id).getMoneyUSD() + amount / moneyRepository.findById(2L).get().getRate());
        }
        if (In.equals("USD") && Out.equals("RUB")) {
            if (usersService.findUserById(id).getMoneyUSD() < amount)
                return false;
            usersService.findUserById(id).setMoneyUSD(usersService.findUserById(id).getMoneyUSD() - amount);
            usersService.findUserById(id).setMoneyRUB(usersService.findUserById(id).getMoneyRUB() + amount * moneyRepository.findById(2L).get().getRate());
        }
        if (In.equals("EUR") && Out.equals("RUB")) {
            if (usersService.findUserById(id).getMoneyEUR() < amount)
                return false;
            usersService.findUserById(id).setMoneyEUR(usersService.findUserById(id).getMoneyEUR() - amount);
            usersService.findUserById(id).setMoneyRUB(usersService.findUserById(id).getMoneyRUB() + amount * moneyRepository.findById(3L).get().getRate());
        }
        if (In.equals("RUB") && Out.equals("EUR")) {
            if (usersService.findUserById(id).getMoneyRUB() < amount)
                return false;
            usersService.findUserById(id).setMoneyRUB(usersService.findUserById(id).getMoneyRUB() - amount);
            usersService.findUserById(id).setMoneyEUR(usersService.findUserById(id).getMoneyEUR() + amount / moneyRepository.findById(3L).get().getRate());
        }
        if (In.equals("FRN") && Out.equals("RUB")) {
            if (usersService.findUserById(id).getMoneyFRN() < amount)
                return false;
            usersService.findUserById(id).setMoneyFRN(usersService.findUserById(id).getMoneyFRN() - amount);
            usersService.findUserById(id).setMoneyRUB(usersService.findUserById(id).getMoneyRUB() + amount * moneyRepository.findById(4L).get().getRate());
        }
        if (In.equals("RUB") && Out.equals("FRN")) {
            if (usersService.findUserById(id).getMoneyRUB() < amount)
                return false;
            usersService.findUserById(id).setMoneyRUB(usersService.findUserById(id).getMoneyRUB() - amount);
            usersService.findUserById(id).setMoneyFRN(usersService.findUserById(id).getMoneyFRN() + amount / moneyRepository.findById(4L).get().getRate());
        }
        if (In.equals("UAN") && Out.equals("RUB")) {
            if (usersService.findUserById(id).getMoneyUAN() < amount)
                return false;
            usersService.findUserById(id).setMoneyUAN(usersService.findUserById(id).getMoneyUAN() - amount);
            usersService.findUserById(id).setMoneyRUB(usersService.findUserById(id).getMoneyRUB() + amount * moneyRepository.findById(5L).get().getRate());
        }
        if (In.equals("RUB") && Out.equals("UAN")) {
            if (usersService.findUserById(id).getMoneyRUB() < amount)
                return false;
            usersService.findUserById(id).setMoneyRUB(usersService.findUserById(id).getMoneyRUB() - amount);
            usersService.findUserById(id).setMoneyUAN(usersService.findUserById(id).getMoneyUAN() + amount / moneyRepository.findById(5L).get().getRate());
        }
        userRepository.save(usersService.findUserById(id));
        return true;
    }
    public List<Money> findAllMoney(){
        return moneyRepository.findAll();
    }
}
