package com.yth.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yth.entity.AccountDetail;
import com.yth.entity.DepositAccount;

/**
 * Account Detail Mapper
 * 
 * @author yth
 *
 */
@Mapper
public interface AccountDetailMapper {
    @Select("select * from AccountDetail order by time desc")
    public List<AccountDetail> getAllAccountDetails();
 
    @Insert("INSERT INTO AccountDetail(id,cardNum,type,change,balance,time) VALUES (#{id},#{cardNum, jdbcType=VARCHAR},#{type},#{change},#{balance},#{time})")
    public int insertLoanAccount(AccountDetail accountDetail);
	
    @Delete("delete from AccountDetail where id=#{id}")
	public int delete(int id);
	

    @Select("SELECT * FROM AccountDetail where  cardNum=#{cardNum}")
    public List<AccountDetail> findDetailByCardNum(String cardNum);
    
    
    

 
}
