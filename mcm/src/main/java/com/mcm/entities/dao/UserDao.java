package com.mcm.entities.dao;

import java.io.IOException;
import java.io.Serializable;
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
	public boolean Register(Device dev) {

		Serializable id = sessionFactory.getCurrentSession().save(dev);

		if (id != null) {
			return true;
		} else {
			return false;
		}
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public void UpdateLocation(Device dev) {

		sessionFactory.getCurrentSession().saveOrUpdate(dev);

	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Device> getAllDevices() {

		List<Device> devices = new ArrayList<Device>();

		devices = sessionFactory.getCurrentSession().createQuery("from Device").list();

		if (devices.size() > 0) {
			return devices;
		} else {
			return null;
		}
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Location> getAllLocations() {

		List<Location> locations = new ArrayList<Location>();

		locations = sessionFactory.getCurrentSession().createQuery("from Location").list();
		System.out.println(locations.get(0).getLatitude());
		return locations;
		
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Device> getDevicesInRange(SubMap sm) {

		List<Device> devices = new ArrayList<Device>();

		devices = sessionFactory.getCurrentSession().createQuery("from Device where id in (SELECT location.phone_id FROM Location"
				+ "WHERE location.latitude BETWEEN (? AND ?) AND location.longtitude BETWEEN (? AND ?))").
				setParameter(0, sm.getMinLat()).setParameter(1, sm.getMaxLat())
				.setParameter(2, sm.getMinLng()).setParameter(3, sm.getMaxLng()).list();

		if (devices.size() > 0) {
			return devices;
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