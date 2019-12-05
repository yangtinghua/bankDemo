package com.yth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.yth.entity.LoanAccount;

/**
 * Loan Account Mapper
 * 
 * @author yth
 *
 */
@Mapper
public interface LoanAccountMapper {
    @Select("select * from LoanAccount")
    public List<LoanAccount> getAllLoans();
 
    @Select("SELECT * FROM LoanAccount where identityNum=#{identityNum} or cardNum=#{cardNum} order by id desc")
    public List<LoanAccount> findLoanByIdentityNumOrCardNum(String identityNum,String cardNum);
    
    @Select("SELECT * FROM LoanAccount where id=#{id}")
    public List<LoanAccount> findLoanById(int id);

    @Insert("INSERT INTO LoanAccount(id,name,identityNum,cardNum,loanLimit,credit,balance,rate,interest,beginTime,endTime,count) VALUES (#{id},#{name, jdbcType=VARCHAR},#{identityNum},#{cardNum},#{loanLimit},#{credit},#{balance},#{rate},#{interest},#{beginTime},#{endTime},#{count})")
    public int insertLoanAccount(LoanAccount loanAccount);
	
    @Delete("delete from LoanAccount where id=#{id}")
	public int delete(int id);
	
    @Update("update LoanAccount set balance=#{balance},loanLimit=#{loanLimit} where id=#{id}")
	public int update(LoanAccount loanAccount);
    
    

 
}
