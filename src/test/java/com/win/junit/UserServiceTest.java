package com.win.junit;
import org.example.User;
import org.example.UserService;
import org.junit.Before;
import org.junit.Test;

import static org.Mockito.Mock;
import static org.junit.Assert.*;

public class UserServiceTest {
    private UserService userService;
    private User testUser;

    @Before
    public void setup(){
        userService = new UserService();
        testUser = new User("","", "");
    }

    //Test methods for register user
    @Test
    public void testRegisterUser_Success(){
        boolean result = userService.registerUser(testUser);
        assertTrue(result);//if Posiitve

    }
    @Test
    public void testRegisterUser_UserAlreadyExist(){
        userService.registerUser(testUser);
        boolean result = userService.registerUser(testUser);
        assertFalse(result);//if Neg
    }
    @Test
    public void testRegisterUser_NullUser() {
        boolean result = userService.registerUser(null);
        assertFalse(result);//edge case
    }

    // test for login user
    @Test
    public void testLoginUser_Success(){
        userService.registerUser(testUser);
        User result = userService.loginUser("", "");
        assertNotNull(result);//pos+ case

    }
    public void testLoginUser_UserNotFound(){
        userService.registerUser(testUser);
        User result = userService.loginUser("", "");
        assertNull(result);//neg- case

    }
    public void testLoginUser_InvalidPassword() {
        userService.registerUser(testUser);
        User result = userService.loginUser("User", "wrongPassword");
        assertNull(result);//edge case
    }
    @Test
    public void testUpdateUserProfile_Success(){
        userService.registerUser(testUser);
        boolean result = userService.updateUserProfile(testUser,"newUsername", "newPassword", "newemail@example.com");
        assertTrue(result);
    }


    @Test
    public void testUpdateUserProfile_NullUser(){
        boolean result = userService.updateUserProfile(null, "newUsername", "newPassword", "newemail@example.com");
        assertFalse(result);//edge case

    }




}
