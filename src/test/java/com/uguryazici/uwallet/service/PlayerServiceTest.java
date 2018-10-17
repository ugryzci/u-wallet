package com.uguryazici.uwallet.service;


import com.uguryazici.uwallet.entity.Player;
import com.uguryazici.uwallet.repository.PlayerRepository;
import com.uguryazici.uwallet.service.impl.PlayerServiceImpl;
import org.assertj.core.util.IterableUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PlayerServiceTest {
    @TestConfiguration
    static class PlayerServiceImplTestConf {
        @Bean
        public PlayerService playerService() {
            return new PlayerServiceImpl();
        }
    }

    @Autowired
    private PlayerService playerService;

    @MockBean
    private PlayerRepository playerRepository;

    @Before
    public void setup() {
        Player playerMock = new Player();
        playerMock.setName("ugur");
        Player playerMock2 = new Player();
        playerMock.setName("ugur2");

        List<Player> playerList = Arrays.asList(playerMock, playerMock2);

        when(playerRepository.findAll()).thenReturn(playerList);
    }

    @Test
    public void findAllPlayersTest() {
        int size = 1;

        Iterable<Player> foundPlayerList = playerService.findAll();
        assertNotEquals(IterableUtil.sizeOf(foundPlayerList), size);
    }
}
