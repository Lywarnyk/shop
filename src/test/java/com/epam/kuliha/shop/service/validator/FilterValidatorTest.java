package com.epam.kuliha.shop.service.validator;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FilterValidatorTest {

    FilterValidator filterValidator;

    @Before
    public void setUp() throws Exception {
        filterValidator = new FilterValidator();
    }

    @Test
    public void isValidInt_PositiveInt_ShouldReturnTrue() {
        assertTrue(filterValidator.isValidInt(1234));
    }

    @Test
    public void isValidInt_NegativeInt_ShouldReturnFalse() {
        assertFalse(filterValidator.isValidInt(-12));
    }

    @Test
    public void isValidName_CorrectNames_ShouldReturnTrue() {
        String name = "TestName";
        String name1 = "Test Name";
        String name2 = "Test., Name";
        String name3 = "testName|1234";

        assertTrue(filterValidator.isValidName(name));
        assertTrue(filterValidator.isValidName(name1));
        assertTrue(filterValidator.isValidName(name2));
        assertTrue(filterValidator.isValidName(name3));
    }

    @Test
    public void isValidName_IncorrectNames_ShouldReturnFalse() {
        String name = "Test@";
        String name1 = "%^&";
        String name2 = ":;asdf";
        String name3 = "; DROP TABLE \"COMPANIES\";-- LTD";

        assertFalse(filterValidator.isValidName(name));
        assertFalse(filterValidator.isValidName(name1));
        assertFalse(filterValidator.isValidName(name2));
        assertFalse(filterValidator.isValidName(name3));
        assertFalse(filterValidator.isValidName(null));
    }

    @Test
    public void isValidItemsPerPage_UnexpectedValues_ShouldReturnFalse() {
        assertFalse(filterValidator.isValidItemsPerPage(0));
        assertFalse(filterValidator.isValidItemsPerPage(1234));
        assertFalse(filterValidator.isValidItemsPerPage(-1));
        assertFalse(filterValidator.isValidItemsPerPage(23));
    }
    @Test
    public void isValidItemsPerPage_ExpectedValues_ShouldReturnTrue() {
        assertTrue(filterValidator.isValidItemsPerPage(3));
        assertTrue(filterValidator.isValidItemsPerPage(5));
        assertTrue(filterValidator.isValidItemsPerPage(50));
        assertTrue(filterValidator.isValidItemsPerPage(15));
    }

    @Test
    public void isValidSort_UnexpectedValues_ShouldReturnFalse() {
        assertFalse(filterValidator.isValidSort("asdf"));
        assertFalse(filterValidator.isValidSort(""));
        assertFalse(filterValidator.isValidSort("123"));
        assertFalse(filterValidator.isValidSort("po imeni"));
    }

    @Test
    public void isValidSort_ExpectedValues_ShouldReturnTrue() {
        assertTrue(filterValidator.isValidSort("name"));
        assertTrue(filterValidator.isValidSort("price"));
    }

    @Test
    public void isValidOrder_ExpectedValues_ShouldReturnTrue() {
        assertTrue(filterValidator.isValidOrder("asc"));
        assertTrue(filterValidator.isValidOrder("desc"));
    }

    @Test
    public void isValidOrder_UnexpectedValues_ShouldReturnFalse() {
        assertFalse(filterValidator.isValidOrder("asd"));
        assertFalse(filterValidator.isValidOrder(""));
        assertFalse(filterValidator.isValidOrder("123"));
        assertFalse(filterValidator.isValidOrder("DESC"));
    }
}