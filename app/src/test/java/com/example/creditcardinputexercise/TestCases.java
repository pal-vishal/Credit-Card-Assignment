package com.example.creditcardinputexercise;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TestCases {

    @Test
    public void isValidCardNumber() {
        String cardNumber = "1234567890";
        Assert.assertThat(String.format("Valid card number test failed for ", cardNumber), MainActivity.isValidCardNumber(cardNumber), is(true));
    }

    @Test
    public void isValidExpiryDate() {
        String expiry = "14/21";
        Assert.assertThat(String.format("Valid expiry date failed for ", expiry), MainActivity.isValidExpiryDate(expiry), is(true));
    }

    @Test
    public void isValidName(){
        String name = "VishPal";
        Assert.assertThat(String.format("Valid name condition failed for ", name), MainActivity.isValidName(name), is(true));

    }

}
