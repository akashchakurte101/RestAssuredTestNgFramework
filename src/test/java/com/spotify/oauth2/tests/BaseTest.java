package com.spotify.oauth2.tests;

import org.testng.annotations.BeforeMethod;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;

public class BaseTest {
    @BeforeMethod
    public void beforeMethod(Method method){
        System.out.println("STARTING TEST: "+method.getName());
        System.out.println("THREAD ID: "+Thread.currentThread().getId());
    }
}
