package com.uguryazici.uwallet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.uguryazici.uwallet.entity.Player;
import com.uguryazici.uwallet.model.PlayerDTO;
import com.uguryazici.uwallet.service.AccountService;
import com.uguryazici.uwallet.service.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    AccountService accountService;

    @MockBean
    private PlayerService playerService;

    private String url = "/player";

    @Test
    public void findAllPlayerTest() throws Exception {
        Player playerMock = new Player();
        playerMock.setName("ugur");
        Player playerMock2 = new Player();
        playerMock.setName("yazici");

        List<Player> playerList = Arrays.asList(playerMock, playerMock2);

        given(playerService.findAll()).willReturn(playerList);

        mockMvc.perform(get(url + "/list")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void addPlayerTest() throws Exception {
        PlayerDTO playerDTOMock = new PlayerDTO();
        playerDTOMock.setName("ugur");

        Player playerMock = new Player();

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(playerDTOMock);

        given(playerService.save(playerMock)).willReturn(playerMock);
        mockMvc.perform(post(url + "/add/")
                .contentType(MediaType.APPLICATION_JSON).content(requestJson))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteByIdTest() throws Exception {
        Player playerMock = new Player();
        playerMock.setName("ugur");
        playerMock.setId(1L);

        mockMvc.perform(delete(url + "/" + playerMock.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
