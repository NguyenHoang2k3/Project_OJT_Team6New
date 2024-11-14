package org.example.projectspringojt.repository.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.projectspringojt.repository.UserCustomerRepository;

public class UserCustomerRepositoryImpl implements UserCustomerRepository {
    @PersistenceContext
    private EntityManager entityManager;
}
