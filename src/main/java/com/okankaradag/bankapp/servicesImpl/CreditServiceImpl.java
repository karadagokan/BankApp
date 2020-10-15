package com.okankaradag.bankapp.servicesImpl;

import com.okankaradag.bankapp.models.Credit;
import com.okankaradag.bankapp.repositories.ICreditDAO;
import com.okankaradag.bankapp.services.ICreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreditServiceImpl implements ICreditService {

    @Autowired
    private ICreditDAO iCreditDAO;

    @Override
    public List<Credit> getAll() {
        return iCreditDAO.findAll();
    }

    @Override
    public Optional<Credit> findById(Integer id) {
        return iCreditDAO.findById(id);
    }

    @Override
    public void save(Credit loan) {
        iCreditDAO.save(loan);
    }

    @Override
    public void delete(Integer id) {
        iCreditDAO.deleteById(id);
    }
}
