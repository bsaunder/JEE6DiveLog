/**
 * 
 */
package net.bryansaunders.jee6divelog.bean;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import net.bryansaunders.jee6divelog.bean.RegistrationBean;
import net.bryansaunders.jee6divelog.model.UserAccount;
import net.bryansaunders.jee6divelog.service.UserAccountService;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for Registration Bean.
 * 
 * @author Bryan Saunders <btsaunde@gmail.com>
 * 
 */
public class RegistrationBeanTest {

    /**
     * Error Template for Confirmation Password NotBlank Validation.
     */
    private static final String CONF_PASSWORD_NOT_BLANK_TEMPLATE = "{regBean.confirmationPassword.notBlank}";

    /**
     * Error Template for Password NotBlank Validation.
     */
    private static final String PASSWORD_NOT_BLANK_TEMPLATE = "{regBean.password.notBlank}";

    /**
     * Error Template for Password Validation.
     */
    private static final Object PASSWORD_ERROR_TEMPLATE = "{regBean.password.password}";

    /**
     * Bean Validator.
     */
    private static Validator validator;

    /**
     * Registration Bean.
     */
    private RegistrationBean regBean;

    /**
     * Valid First Name.
     */
    private final String fName = "Bryan";

    /**
     * Valid Last Name.
     */
    private final String lName = "Saunders";

    /**
     * Valid Country.
     */
    private final String country = "USA";

    /**
     * Valid State.
     */
    private final String state = "South Carolina";

    /**
     * Valid City.
     */
    private final String city = "Charleston";

    /**
     * Valid Email.
     */
    private final String email = "btsaunde@gmail.com";

    /**
     * Valid Password.
     */
    private final String validPassword = "abcdef1A@";

    /**
     * Alternate Valid Password.
     */
    private final String validPassword2 = "abcdef2A@";
    
    /**
     * User Account Service.
     */
    private UserAccountService accountService;

    /**
     * Sets up the test class.
     */
    @BeforeClass
    public static void setUp() {
        final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        RegistrationBeanTest.validator = factory.getValidator();
    }

    /**
     * Sets up the test cases.
     */
    @Before
    public void setUpTest() {
        this.regBean = new RegistrationBean();

        this.regBean.setFirstName(this.fName);
        this.regBean.setLastName(this.lName);
        this.regBean.setCountry(this.country);
        this.regBean.setState(this.state);
        this.regBean.setCity(this.city);
        this.regBean.setEmail(this.email);
        this.regBean.setPassword(this.validPassword);
        this.regBean.setConfirmationPassword(this.validPassword);
        
        this.accountService = mock(UserAccountService.class);
        this.regBean.setUserService(this.accountService);
    }

    /**
     * Tests Bean Creation.
     */
    @Test
    public void beanCreation() {
        assertNotNull(this.regBean);
    }

    /**
     * Tests if the First Name is Valid.
     */
    @Test
    public void ifFirstNameValidThenSuccess() {
        assertEquals(this.fName, this.regBean.getFirstName());

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Tests if the First Name is Valid.
     */
    @Test
    public void ifFirstNameBlankThenFail() {
        this.regBean.setFirstName("");

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(1, constraintViolations.size());
        assertEquals("{regBean.firstName.notBlank}", constraintViolations.iterator().next().getMessageTemplate());
    }

    /**
     * Tests if the First Name is Valid.
     */
    @Test
    public void ifFirstNameNullThenFail() {
        this.regBean.setFirstName(null);

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(1, constraintViolations.size());
        assertEquals("{regBean.firstName.notBlank}", constraintViolations.iterator().next().getMessageTemplate());
    }

    /**
     * Tests if the Last Name is Valid.
     */
    @Test
    public void ifLastNameValidThenSuccess() {
        assertEquals(this.lName, this.regBean.getLastName());

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Tests if the Last Name is Valid.
     */
    @Test
    public void ifLastNameBlankThenFail() {
        this.regBean.setLastName("");

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(1, constraintViolations.size());
        assertEquals("{regBean.lastName.notBlank}", constraintViolations.iterator().next().getMessageTemplate());
    }

    /**
     * Tests if the Last Name is Valid.
     */
    @Test
    public void ifLastNameNullThenFail() {
        this.regBean.setLastName(null);

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(1, constraintViolations.size());
        assertEquals("{regBean.lastName.notBlank}", constraintViolations.iterator().next().getMessageTemplate());
    }

    /**
     * Tests if the Country is Valid.
     */
    @Test
    public void ifCountryValidThenSuccess() {
        assertEquals(this.country, this.regBean.getCountry());

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Tests if the City is Valid.
     */
    @Test
    public void ifCityValidThenSuccess() {
        assertEquals(this.city, this.regBean.getCity());

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Tests if the State is Valid.
     */
    @Test
    public void ifStateValidThenSuccess() {
        assertEquals(this.state, this.regBean.getState());

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Tests if the Email is Valid.
     */
    @Test
    public void ifEmailValidThenSuccess() {
        assertEquals(this.email, this.regBean.getEmail());

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Tests if the Email is Valid.
     */
    @Test
    public void ifEmailBlankThenFail() {
        this.regBean.setEmail("");

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(1, constraintViolations.size());
        assertEquals("{regBean.email.notBlank}", constraintViolations.iterator().next().getMessageTemplate());
    }

    /**
     * Tests if the Email is Valid.
     */
    @Test
    public void ifEmailNullThenFail() {
        this.regBean.setEmail(null);

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(1, constraintViolations.size());
        assertEquals("{regBean.email.notBlank}", constraintViolations.iterator().next().getMessageTemplate());
    }

    /**
     * Tests if the Email is Valid.
     */
    @Test
    public void ifEmailInvalidThenFail() {
        this.regBean.setEmail("asasdasd");

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(1, constraintViolations.size());
        assertEquals("{regBean.email.email}", constraintViolations.iterator().next().getMessageTemplate());
    }

    /**
     * Tests if the Password is Valid.
     */
    @Test
    public void ifPasswordValidThenSuccess() {
        assertEquals(this.validPassword, this.regBean.getPassword());

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Tests if the Password is Valid.
     */
    @Test
    public void ifPasswordBlankThenFail() {
        this.regBean.setPassword("");
        this.regBean.setConfirmationPassword("");

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(3, constraintViolations.size());

        boolean passwordNotBlank = false;
        boolean confPasswordNotBlank = false;
        boolean password = false;
        final Iterator<ConstraintViolation<RegistrationBean>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            final ConstraintViolation<RegistrationBean> violation = iterator.next();
            final String msgTemplate = violation.getMessageTemplate();
            if (msgTemplate.equals(CONF_PASSWORD_NOT_BLANK_TEMPLATE)) {
                confPasswordNotBlank = true;
            } else if (msgTemplate.equals(PASSWORD_NOT_BLANK_TEMPLATE)) {
                passwordNotBlank = true;
            } else if (msgTemplate.equals(PASSWORD_ERROR_TEMPLATE)) {
                password = true;
            }
        }

        assertTrue(passwordNotBlank && confPasswordNotBlank && password);
    }

    /**
     * Tests if the Password is Valid.
     */
    @Test
    public void ifPasswordNullThenFail() {
        this.regBean.setPassword(null);
        this.regBean.setConfirmationPassword(null);

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(3, constraintViolations.size());

        boolean passwordNotBlank = false;
        boolean confPasswordNotBlank = false;
        boolean password = false;
        final Iterator<ConstraintViolation<RegistrationBean>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            final ConstraintViolation<RegistrationBean> violation = iterator.next();
            final String msgTemplate = violation.getMessageTemplate();
            if (msgTemplate.equals(CONF_PASSWORD_NOT_BLANK_TEMPLATE)) {
                confPasswordNotBlank = true;
            } else if (msgTemplate.equals(PASSWORD_NOT_BLANK_TEMPLATE)) {
                passwordNotBlank = true;
            } else if (msgTemplate.equals(PASSWORD_ERROR_TEMPLATE)) {
                password = true;
            }
        }

        assertTrue(passwordNotBlank && confPasswordNotBlank && password);
    }

    /**
     * Tests if the Password is Valid.
     */
    @Test
    public void ifPasswordInvalidThenFail() {
        this.regBean.setPassword("rtrt");
        this.regBean.setConfirmationPassword("rtrt");

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(1, constraintViolations.size());
        assertEquals(PASSWORD_ERROR_TEMPLATE, constraintViolations.iterator().next().getMessageTemplate());
    }

    /**
     * Tests if the Confirmation Password is Valid.
     */
    @Test
    public void ifConfirmationPasswordValidThenSuccess() {
        assertEquals(this.validPassword, this.regBean.getConfirmationPassword());

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Tests if the Confirmation Password is Valid.
     */
    @Test
    public void ifConfirmationPasswordBlankThenFail() {
        this.regBean.setPassword("");
        this.regBean.setConfirmationPassword("");

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(3, constraintViolations.size());

        boolean passwordNotBlank = false;
        boolean confPasswordNotBlank = false;
        boolean password = false;
        final Iterator<ConstraintViolation<RegistrationBean>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            final ConstraintViolation<RegistrationBean> violation = iterator.next();
            final String msgTemplate = violation.getMessageTemplate();
            if (msgTemplate.equals(CONF_PASSWORD_NOT_BLANK_TEMPLATE)) {
                confPasswordNotBlank = true;
            } else if (msgTemplate.equals(PASSWORD_NOT_BLANK_TEMPLATE)) {
                passwordNotBlank = true;
            } else if (msgTemplate.equals(PASSWORD_ERROR_TEMPLATE)) {
                password = true;
            }
        }

        assertTrue(passwordNotBlank && confPasswordNotBlank && password);
    }

    /**
     * Tests if the Confirmation Password is Valid.
     */
    @Test
    public void ifConfirmationPasswordNullThenFail() {
        this.regBean.setPassword(null);
        this.regBean.setConfirmationPassword(null);

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(3, constraintViolations.size());

        boolean passwordNotBlank = false;
        boolean confPasswordNotBlank = false;
        boolean password = false;
        final Iterator<ConstraintViolation<RegistrationBean>> iterator = constraintViolations.iterator();
        while (iterator.hasNext()) {
            final ConstraintViolation<RegistrationBean> violation = iterator.next();
            final String msgTemplate = violation.getMessageTemplate();
            if (msgTemplate.equals(CONF_PASSWORD_NOT_BLANK_TEMPLATE)) {
                confPasswordNotBlank = true;
            } else if (msgTemplate.equals(PASSWORD_NOT_BLANK_TEMPLATE)) {
                passwordNotBlank = true;
            } else if (msgTemplate.equals(PASSWORD_ERROR_TEMPLATE)) {
                password = true;
            }
        }

        assertTrue(passwordNotBlank && confPasswordNotBlank && password);
    }

    /**
     * Tests passwords match.
     */
    @Test
    public void ifPasswordsMatchThenPass() {
        // Passwords were set when the bean was created.

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(0, constraintViolations.size());
    }

    /**
     * Tests passwords match.
     */
    @Test
    public void ifPasswordsDontMatchThenFail() {
        this.regBean.setPassword(this.validPassword);
        this.regBean.setConfirmationPassword(this.validPassword2);

        final Set<ConstraintViolation<RegistrationBean>> constraintViolations = RegistrationBeanTest.validator
                .validate(this.regBean);
        assertEquals(1, constraintViolations.size());
    }

    /**
     * Tests Valid Form Submission.
     */
    @Test
    public void ifRegistrationValidThenSuccess() {
        // given
        final UserAccount userAccount = this.regBean.createUser();
        userAccount.setId(1);
        userAccount.setVersion(0);
        
        // when
        when(this.accountService.createUser(any(UserAccount.class))).thenReturn(userAccount);
        final String result = this.regBean.submitRegistration();
        
        // then
        assertEquals(RegistrationBean.SUCCESS, result);
    }

    /**
     * Tests Invalid Form Submission.
     */
    @Test
    public void ifRegistrationInvalidThenFailure() {
        // given
        
        // when
        when(this.accountService.createUser(any(UserAccount.class))).thenReturn(null);
        final String result = this.regBean.submitRegistration();
        
        // then
        assertEquals(RegistrationBean.FAILURE, result);
    }
    
    /**
     * Tests Create User.
     */
    @Test
    public void createUser(){
        final UserAccount user = this.regBean.createUser();
        
        assertEquals(user.getCity(), this.city);
        assertEquals(user.getCountry(), this.country);
        assertEquals(user.getState(), this.state);
        assertEquals(user.getEmail(), this.email);
        assertEquals(user.getPassword(), this.regBean.getPassword());
        assertEquals(user.getFirstName(), this.fName);
        assertEquals(user.getLastName(), this.lName);
    }

}
