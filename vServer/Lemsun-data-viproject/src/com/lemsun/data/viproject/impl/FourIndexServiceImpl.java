package com.lemsun.data.viproject.impl;

import com.lemsun.data.viproject.FourIndex;
import com.lemsun.data.viproject.IFourIndexService;
import com.lemsun.data.viproject.repository.FourAccountRepository;
import com.lemsun.data.viproject.repository.FourIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-1-19
 * Time: 上午10:30
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FourIndexServiceImpl implements IFourIndexService {

    private FourIndexRepository fourIndexRepository;
    private FourAccountRepository fourAccountRepository;
    @Autowired
    public FourIndexServiceImpl(
            FourIndexRepository fourIndexRepository,
            FourAccountRepository fourAccountRepository) {
        this.fourIndexRepository=fourIndexRepository;
        this.fourAccountRepository=fourAccountRepository;
    }
    @Override
    public FourIndex getFourIndexTree(JdbcTemplate jdbcTemplate, String accountCode, Date date) {
        FourIndex fourIndex= fourIndexRepository.getFourAccountBycode(jdbcTemplate, accountCode);//此时帐套也算目录
        if(fourIndex==null)return null;
        return fourIndexRepository.getFourIndexByAccount(jdbcTemplate, fourIndex, date);
    }
}
