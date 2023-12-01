package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest(showSql = false)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTests {
    @Autowired
    private UserRepository repo;
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Test
    public void testCreateNewUserWithOneRole() {
    	Role roleAdmin = entityManager.find(Role.class, 1);
    	User userMinhVQ = new User("vuquangminh872000@gmail.com","minhvu87","Minh", "Vu Quang");
    	userMinhVQ.addRole(roleAdmin);
    	
    	User savedUser = repo.save(userMinhVQ);
    	
    	assertThat(savedUser.getId()).isGreaterThan(0);
    }
    
    @Test
    public void testCreateNewUserWithTwoRoles() {
    	User userRavi = new User("ravi@gmail.com","ravi2020","Ravi","Kumar");
    	Role roleEditor = new Role(3);
    	Role roleAssistant = new Role(5);
   
    	userRavi.addRole(roleEditor);
    	userRavi.addRole(roleAssistant);
    	
    	User savedUser = repo.save(userRavi);
    	
    	assertThat(savedUser.getId()).isGreaterThan(0);
    }
    
    @Test
    public void testListAllUsers() {
    	Iterable<User> listUsers = repo.findAll();
    	listUsers.forEach(user -> System.out.println(user));
    }
    
    @Test
    public void testGetUserById() {
    	User userMinh = repo.findById(1).get();
    	System.out.println(userMinh);
    	assertThat(userMinh).isNotNull();
    }
    
    @Test
    public void testUpdateUserDetails() {
    	User userMinh = repo.findById(30).get();
    	
    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    	String rawPassword = "minhvu87";
    	String encodedPassword = passwordEncoder.encode(rawPassword);
    	userMinh.setPassword(encodedPassword);
    	
    	repo.save(userMinh);
    }
    
    @Test
    public void testUpdateUserRoles() {
    	User userRavi = repo.findById(9).get();
    	Role roleEditor = new Role(3);
    	Role roleSaleperson = new Role(2);
    	
    	userRavi.getRoles().remove(roleEditor);
    	userRavi.addRole(roleSaleperson);
    	
    	repo.save(userRavi);
    }
    
    @Test
    public void testDeleteUser() {
    	Integer userId = 1;
    	repo.deleteById(userId);
    }
    
    @Test
    public void testGetUserByEmail() {
    	String email = "vuquangminh872000@gmail.com";
    	User user = repo.getUserByEmail(email);
    	
    	assertThat(user).isNotNull();
    }
    
    @Test
    public void testCountById() {
    	Integer id = 21;
    	Long countById = repo.countById(id);
    	
    	assertThat(countById).isNotNull().isGreaterThan(0);
    }
    
    @Test
    public void testDisableUser() {
    	Integer id = 30;
    	repo.updateEnabledStatus(id, true);
    }
    
    @Test
    public void testEnableUser() {
    	Integer id = 9;
    	repo.updateEnabledStatus(id, true);
    }
    
    @Test
    public void testListFirstPage() {
    	int pageNumber = 1;
    	int pageSize = 4;
    	
    	Pageable pageable = PageRequest.of(pageNumber, pageSize);
    	Page<User> page = repo.findAll(pageable);
    	
    	List<User> listUsers = page.getContent();
    	
    	listUsers.forEach(user -> System.out.println(user));
    	
    	assertThat(listUsers.size()).isEqualTo(pageSize);
    }
    
    @Test
    public void testSearchUsers() {
    	String keyword = "bruce";
    	
    	int pageNumber = 0;
    	int pageSize = 4;
    	
    	Pageable pageable = PageRequest.of(pageNumber, pageSize);
    	Page<User> page = repo.findAll(keyword, pageable);
    	
    	List<User> listUsers = page.getContent();
    	
    	listUsers.forEach(user -> System.out.println(user));
    	
    	assertThat(listUsers.size()).isGreaterThan(0);
    	
    }
    
}
