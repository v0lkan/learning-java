package io.volkan.repositories;

import io.volkan.config.AppConfig;
import io.volkan.entities.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.number.BigDecimalCloseTo.closeTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
@Transactional
public class AccountRepositoryTest {

    @Autowired
    private AccountRepository repository;

    @Test
    public void testGetAccounts() throws Exception {
        List<Account> accounts = repository.findAll();

        assertThat(accounts.size(), is(3));
    }

    @Test
    public void testGetAccount() throws Exception {
        Account account = repository.findOne(1L);

        assertThat(account.getId(), is(1L));
        assertThat(new BigDecimal("100.0"), is(closeTo(account.getBalance(), new BigDecimal("0.01"))));
    }

    @Test
    public void testGetNumberOfAccounts() throws Exception {
        assertThat(repository.count(), is(3L));
    }

    @Test
    public void testCreateAccount() throws Exception {
        Account account = new Account(99L, new BigDecimal("250.00"));

        repository.save(account);

        Long id = account.getId();

        assertThat(id, is(notNullValue()));

        Account again = repository.findOne(id);
        assertThat(again.getBalance(), is(closeTo(new BigDecimal("250.00"), new BigDecimal("0.01"))));
    }

    @Test
    public void testUpdateAccount() throws Exception {
        Account account = repository.findOne(1L);
        BigDecimal current = account.getBalance();
        BigDecimal amount = new BigDecimal("50.0");

        account.setBalance(current.add(amount));
        repository.save(account);

        Account again = repository.findOne(1L);

        assertThat(again.getBalance(), is(closeTo(current.add(amount), new BigDecimal("0.01"))));
    }

    @Test
    public void testAccountsBalanceGTE() throws Exception {
        List<Account> accounts = repository.findAccountsByBalanceGreaterThanEqual(new BigDecimal("100.0"));

        assertThat(accounts.size(), is(3));
    }
}
