package com.gamedirectory;

import com.gamedirectory.model.Gamer;
import com.gamedirectory.model.UserServiceResponse;
import com.gamedirectory.service.GameDirService;
import com.gamedirectory.service.RestMessagingSvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.powermock.api.mockito.PowerMockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameDirServiceTest {

    @InjectMocks
    @Spy
    GameDirService gameDirService = new GameDirService();

    @Mock
    RestMessagingSvc restMessagingSvc;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        restMessagingSvc = Mockito.mock(RestMessagingSvc.class);
    }
    /*
    @Test
    public void testGetuserInfo(){

        List<Gamer> gamers = new ArrayList<>();

        UserServiceResponse mockResp = new UserServiceResponse(200, "Success",gamers);

        PowerMockito.doReturn(mockResp).when(restMessagingSvc).sendGetRequest("http://localhost:8082/user", UserServiceResponse.class);

        UserServiceResponse response = gameDirService.getUsersInfo();

        assertEquals(200, response.getResponseCode());
    }
    
     */




}
