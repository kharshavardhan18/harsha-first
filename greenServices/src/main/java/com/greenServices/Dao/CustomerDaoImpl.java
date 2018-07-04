package com.greenServices.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.greenServices.bean.AccountList;
import com.greenServices.bean.CustomerList;
import com.greenServices.bean.RegisterForm;
import com.greenServices.contract.CustomerListContract;
import com.greenServices.entities.CustomerEntity;
import com.greenServices.entities.SecAccounts;


@Transactional
@Repository("customerDaoImpl")
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated methClass.forName("com.mysql.jdbc.Driver");  
		System.out.println("Connection establishing in getconnection() DAO ");
		Class.forName("com.mysql.jdbc.Driver"); 

		Connection con= DriverManager.getConnection(DB_URL,"root","1234");
		System.out.println("after connecting to jdbc ");
		return con;
	}


	@Override
	public CustomerListContract fetchByUserName(String userName, String password)  {
		// TODO Auto-generated method stub

		System.out.println("in DAO CLASS fetch by userName()****");
		/*Connection con=getConnection();
		System.out.println("got the connction and entered into fetch()***");
		String query="select * from sec_vault.customer where username= ?";
		PreparedStatement preStmt=con.prepareStatement(query);
		preStmt.setString(1, userName);
		ResultSet rset=preStmt.executeQuery();

		CustomerList customerList=null;
		if(rset.next())
		{
			System.out.println(password +"our pass");
			System.out.println("in resultset");
			System.out.println(rset.getString("password"));
			if(rset.getString("password").equals(password) )
			{
				System.out.println(rset.getString("firstname") +"  firstname in result");

				System.out.println("in if passsword condition");
				customerList=new CustomerList();
				customerList.setId(rset.getInt("id"));
				customerList.setUsername(rset.getString("username"));
				customerList.setFirstname(rset.getString("firstname"));
				customerList.setLastname(rset.getString("lastname"));
				customerList.setEmail(rset.getString("email"));

			}
		}*/

		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(CustomerEntity.class);
		criteria.add(Restrictions.eq("username", userName));
		CustomerEntity customerEntity=(CustomerEntity) criteria.uniqueResult();
		CustomerListContract customerList=null;;


		if(customerEntity.getPassword().equals(password))
		{
			customerList=new CustomerListContract();
			customerList.setId(customerEntity.getId());
			customerList.setUsername(customerEntity.getUsername());
			customerList.setFirstname(customerEntity.getFirstname());
			customerList.setLastname(customerEntity.getLastname());
			customerList.setCreate_date(customerEntity.getCreate_date());
			customerList.setDob(customerEntity.getDob());
			customerList.setEmail(customerEntity.getEmail());
			customerList.setGender(customerEntity.getGender());
			customerList.setUpdate_date(customerEntity.getUpdate_date());
			customerList.setPassword(customerEntity.getPassword());

		}

		return customerList;
	}


	@Override
	public boolean addRequest(RegisterForm registerForm) throws ClassNotFoundException,SQLException, ParseException {
		/*Connection con=getConnection();
		String query="INSERT INTO sec_vault.customer(username,firstname,lastname,gender,email,"+
				"dob,password,create_date)"+"VALUES(?,?,?,?,?,?,?,?)";

		java.util.Date dob=new SimpleDateFormat("MM/dd/yyyy").parse(registerForm.getDob()); 
		java.sql.Date birthday=new java.sql.Date(dob.getTime());

		Calendar calendar=Calendar.getInstance();
		java.sql.Date date=new java.sql.Date(calendar.getTime().getTime());

		PreparedStatement prepStmt=con.prepareStatement(query);
		prepStmt.setString(1, registerForm.getUserName());
		prepStmt.setString(2, registerForm.getFirstName());
		prepStmt.setString(3,registerForm.getLastName());
		prepStmt.setString(4, registerForm.getGender());
		prepStmt.setString(5, registerForm.getEmail());
		prepStmt.setDate(6, birthday);
		prepStmt.setString(7, registerForm.getPassword());
		prepStmt.setDate(8, date);

		return prepStmt.execute();	*/

		java.util.Date dob=new SimpleDateFormat("MM/dd/yyyy").parse(registerForm.getDob()); 


		Calendar calendar=Calendar.getInstance();
		java.util.Date currentDate=new java.util.Date(calendar.getTime().getTime());

		CustomerEntity customerEntity=new CustomerEntity();

		customerEntity.setUsername(registerForm.getUserName());
		customerEntity.setFirstname(registerForm.getFirstName());
		customerEntity.setLastname(registerForm.getLastName());
		customerEntity.setGender(registerForm.getGender());
		customerEntity.setEmail(registerForm.getEmail());
		customerEntity.setDob(dob);
		customerEntity.setPassword(registerForm.getPassword());
		customerEntity.setCreate_date(currentDate);

		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.save(customerEntity);
		session.getTransaction().commit();
		session.close();
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SecAccounts> fetchAllSecAccounts() {
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(SecAccounts.class);
		List<SecAccounts> secAccounts=criteria.list();
		return secAccounts;
	}




	@Override
	public List<SecAccounts> addAccount(int userId,AccountList accountList){
		SecAccounts secAccounts=new SecAccounts();
		//---add all properties of accountList into entity class SecAccounts
		secAccounts.setUser_id(userId);
		secAccounts.setCompany_name(accountList.getCompany_name());
		secAccounts.setCountry(accountList.getCountry());
		secAccounts.setAccount_username(accountList.getAccount_username());
		secAccounts.setPassword(accountList.getPassword());
		secAccounts.setAccount_email(accountList.getAccount_email());
		secAccounts.setCompany_url(accountList.getCompany_url());

		Session session= sessionFactory.openSession();
		session.beginTransaction();
		session.save(secAccounts);
		session.getTransaction().commit();
		session.close();
		return fetchByUserId(userId);
	}


	@Override
	public List<SecAccounts> fetchByUserId(int userId) {

		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(SecAccounts.class);
		criteria.add(Restrictions.eq("user_id", userId));
		List<SecAccounts> secAccounts= criteria.list();
		return secAccounts;
	}


	@Override
	public List<SecAccounts> searchByKeyword(int userId, String key) {

		System.out.println("in Dao searchByKeyword()");

		List<SecAccounts> accounts=fetchByUserId(userId);
		System.out.println("list of accounts by userId and size is"+accounts.size());

		List<SecAccounts> accListByKey=new ArrayList<SecAccounts>();
		for(SecAccounts account:accounts)
		{
			System.out.println("in for looop --company name="+account.getCompany_name());
			if(account.getCompany_name().contains(key)|| account.getAccount_username().contains(key)||account.getCountry().contains(key))
			{
				accListByKey.add(account);
			}
		}
		return accListByKey;
	}


	@Override
	public CustomerEntity fetchByUsername(String username) {


		Session session= sessionFactory.openSession();
		session.beginTransaction();
		Criteria criteria=session.createCriteria(CustomerEntity.class);
		criteria.add(Restrictions.eq("username", username));
		@SuppressWarnings("unchecked")
		List<CustomerEntity> customer=criteria.list();
		return customer.get(0);
	}

}
