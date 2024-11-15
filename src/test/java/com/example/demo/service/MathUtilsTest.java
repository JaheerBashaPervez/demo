package com.example.demo.service;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MathUtilsTest {
    CustomerOperations mathUtils;

    @BeforeAll
    static void dataSetup(){
        System.out.println("Data initialization setup");
    }

    @BeforeEach
    void init(){
        mathUtils = new CustomerOperations();
    }

    @RepeatedTest(1)
    void addMethodTest(){
        assertEquals(5,mathUtils.add(2,3));
    }

    @Test
    void addMethodTest2(){
        assertEquals(4, mathUtils.add(2,3), "Addition isn't working as expected");
    }

    @Test
    @DisplayName("EXCEPTION_CHECKING_TEST")
    void exceptionCheck(){
        assertThrows(ArithmeticException.class, ()->mathUtils.getName("Jaheer")) ;
    }
}

