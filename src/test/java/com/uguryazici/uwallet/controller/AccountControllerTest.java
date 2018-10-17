package com.uguryazici.uwallet.controller;

import com.uguryazici.uwallet.entity.Account;
import com.uguryazici.uwallet.entity.Player;
import com.uguryazici.uwallet.service.AccountService;
import com.uguryazici.uwallet.service.PlayerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @MockBean
    private AccountService accountService;

    @MockBean
    private PlayerService playerService;

    private String url = "/account";

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
        Player playerMock = new Player();
        playerMock.setId(1L);
    }

    @Test
    public void addAccountToPlayerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/account/addAccountToPlayer/").
                contentType(MediaType.APPLICATION_JSON).content("{\"balance\": 23, \"name\": \"ugryzci\" , \"playerId\": 1}")
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
    }

    @Test
    public void deleteByIdTest() throws Exception {
        Account accountMock = new Account();
        accountMock.setName("stockholm");
        accountMock.setId(1L);

        mockMvc.perform(delete(url + "/" + accountMock.getId())
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }

    @Test
    public void findAllAccountTest() throws Exception {
        Account accountMock = new Account();
        accountMock.setName("istanbul");
        Account accountMock2 = new Account();
        accountMock.setName("stockholm");

        List<Account> accountList = Arrays.asList(accountMock, accountMock2);

        given(accountService.findAll()).willReturn(accountList);

        mockMvc.perform(get(url + "/list")
                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
