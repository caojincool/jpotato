package com.lemsun.data.viproject.impl;

import com.lemsun.data.viproject.FourIndex;
import com.lemsun.data.viproject.FourTableGroup;
import com.lemsun.data.viproject.IFourTableGroupService;
import com.lemsun.data.viproject.repository.FourIndexRepository;
import com.lemsun.data.viproject.repository.FourTableGroupRepository;
import com.lemsun.data.viproject.util.FourCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 表组操作类
 * User: Lucklim
 * Date: 13-1-19
 * Time: 上午10:34
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FourTableGroupServiceImpl implements IFourTableGroupService {

    private FourTableGroupRepository fourTablegroupRepository;
    private FourIndexRepository fourIndexRepository;

    @Autowired
    public FourTableGroupServiceImpl(FourTableGroupRepository fourTablegroupRepository,
                                     FourIndexRepository fourIndexRepository)   {
        this.fourTablegroupRepository=fourTablegroupRepository;
        this.fourIndexRepository=fourIndexRepository;
    }

    @Override
    public FourTableGroup getVinavigateBycode(JdbcTemplate jdbcTemplate, String code) {
        return fourTablegroupRepository.getFourTableGroupBycode(jdbcTemplate, code);
    }

    @Override
    public List<FourTableGroup> getFourTableGroupByIndex(JdbcTemplate jdbcTemplate, String indexCode, Date date) {
        FourIndex fourIndex= fourIndexRepository.getFourAccountBycode(jdbcTemplate, indexCode);
        if(fourIndex==null)return null;
        return fourTablegroupRepository.getFourTableGroupByIndex(jdbcTemplate, FourCommon.getFourTableName(fourIndex.getCode(), date, fourIndex.getNameFormat()));
    }

    @Override
    public List<FourTableGroup> getAllNvNavigate(JdbcTemplate jdbcTemplate) {
        return fourTablegroupRepository.getAllFourTableGroup(jdbcTemplate);
    }
}
