package com.zonesion.cloud.service;

import com.zonesion.cloud.ZonesionCloudApplicationApp;
import com.zonesion.cloud.domain.User;
import com.zonesion.cloud.config.Constants;
import com.zonesion.cloud.repository.UserRepository;
import com.zonesion.cloud.service.dto.UserDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

/**
 * Test class for the UserResource REST controller.
 *
 * @see UserService
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ZonesionCloudApplicationApp.class)
@Transactional
public class UserServiceIntTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Test
    public void testFindNotActivatedUsersByCreationDateBefore() {
        userService.removeNotActivatedUsers();
        Instant now = Instant.now();
        List<User> users = userRepository.findAllByActivatedIsFalseAndCreatedDateBefore(now.minus(3, ChronoUnit.DAYS));
        assertThat(users).isEmpty();
    }

    @Test
    public void testRemoveNotActivatedUsers() {
        User user = userService.createUser("johndoe", "johndoe", "John Doe", "john.doe@localhost", "http://placehold.it/50x50");
        user.setActivated(false);
        user.setCreatedDate(Instant.now().minus(30, ChronoUnit.DAYS));
        userRepository.save(user);
        assertThat(userRepository.findOneByLogin("johndoe")).isPresent();
        userService.removeNotActivatedUsers();
        assertThat(userRepository.findOneByLogin("johndoe")).isNotPresent();
    }
}
