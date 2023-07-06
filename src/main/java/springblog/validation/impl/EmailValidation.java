package springblog.validation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import springblog.persistence.dao.user.UserDao;
import springblog.persistence.entity.User;
import springblog.validation.EmailExit;

public class EmailValidation implements ConstraintValidator<EmailExit, String> {

    @Autowired
    private UserDao userDao;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		User user = userDao.findByEmail(value);
		return user != null;
	}
}
