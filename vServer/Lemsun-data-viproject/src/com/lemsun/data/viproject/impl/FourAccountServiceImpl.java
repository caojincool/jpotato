package com.lemsun.data.viproject.impl;

import com.lemsun.data.viproject.FourAccount;
import com.lemsun.data.viproject.IFourAccountService;
import com.lemsun.data.viproject.repository.FourAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-19
 * Time: 上午10:28
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FourAccountServiceImpl implements IFourAccountService {
    private FourAccountRepository fourAccountRepository;
    @Autowired
    public FourAccountServiceImpl(
            FourAccountRepository fourAccountRepository) {
        this.fourAccountRepository=fourAccountRepository;
    }


    @Override
    public List<FourAccount> getFourAccount(JdbcTemplate jdbcTemplate, String accountname) {
        return fourAccountRepository.getFourAccount(jdbcTemplate,accountname);
    }

    @Override
    public FourAccount getFourAccountBycode(JdbcTemplate jdbcTemplate, String code) {
        return fourAccountRepository.getFourAccountBycode(jdbcTemplate,code);
    }
}
