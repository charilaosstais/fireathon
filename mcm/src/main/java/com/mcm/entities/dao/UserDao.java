package com.mcm.entities.dao;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.mcm.config.Constants;
import com.mcm.config.Constants.BOOLEAN_FLAG_VALUE;
import com.mcm.entities.model.*;


@Repository
public class UserDao {
	@Autowired
	private SessionFactory sessionFactory;

	public static final Logger logger = LoggerFactory.getLogger(UserDao.class);

	public static void logError(String msg) {
		logger.error("*** ERROR *** " + msg);
	}

	public static void logInfo(String msg) {
		logger.debug("*** INFO *** " + msg);
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public Actor getUserByUserName(String username) {

		List<Actor> users = new ArrayList<Actor>();

		users = sessionFactory.getCurrentSession().createQuery("from User where username=?").setParameter(0, username)
				.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public Actor getUserByEmail(String email) {

		List<Actor> users = new ArrayList<Actor>();

		users = sessionFactory.getCurrentSession().createQuery("from User where email=?").setParameter(0, email)
				.list();

		if (users.size() > 0) {
			return users.get(0);
		} else {
			return null;
		}
	}
	
}