package dbUtil.service.impl;

import java.sql.Connection;
import java.sql.SQLException;

import dbUtil.dao.impl.AccountDaoImpl;
import dbUtil.domain.Account;
import dbUtil.util.DBCPUtil;
import dbUtil.util.TransactionUtil;


public class AccountServiceImpl {
	
//	public void transfer(String sourceAccountName,String targetAccountName,float money){
//		Connection conn = null;
//		try{
//			conn = DBCPUtil.getConnection();
//			conn.setAutoCommit(false);
//			AccountDaoImpl dao = new AccountDaoImpl(conn);
//			Account sAccount = dao.findAccount(sourceAccountName);
//			Account tAccount = dao.findAccount(targetAccountName);
//			sAccount.setMoney(sAccount.getMoney()-money);
//			tAccount.setMoney(tAccount.getMoney()+money);
//			dao.updateAccount(sAccount);
//			int i=1/0;
//			dao.updateAccount(tAccount);
//		}catch(Exception e){
//			try {
//				conn.rollback();
//			} catch (SQLException e1) {
//				e1.printStackTrace();
//			}
//			e.printStackTrace();
//		}finally{
//			try {
//				conn.commit();
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	
	private AccountDaoImpl dao = new AccountDaoImpl();
	public void transfer(String sourceAccountName,String targetAccountName,float money){
		try{
			TransactionUtil.startTransaction();
			Account sAccount = dao.findAccount(sourceAccountName);
			Account tAccount = dao.findAccount(targetAccountName);
			sAccount.setMoney(sAccount.getMoney()-money);
			tAccount.setMoney(tAccount.getMoney()+money);
			dao.updateAccount(sAccount);
//			int i=1/0;
			dao.updateAccount(tAccount);
		}catch(Exception e){
			TransactionUtil.rollback();
			e.printStackTrace();
		}finally{
			TransactionUtil.commit();
			TransactionUtil.relase();
		}
	}
}
