package com.thoughtworks.twu.domain;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class UserTest
{
    private User user;
    @Before
    public void setUp(){
    user = new User("yding@thoughtworks.com", "Yue", "123", "13991871507");

    }

    @Test
    public void testGetName()  {
        assertThat(user.getUsername(),is("Yue"));

    }

    @Test
    public void testGetEmail() {
        assertThat(user.getEmail(),is("yding@thoughtworks.com"));
    }
    @Test
    public void testGetPassword() {
    assertThat(user.getPassword(),is("123"));
    }

    @Test
    public void testGetPhoneNumber() {
         assertThat(user.getPhoneNumber(),is("13991871507"));
    }
}
