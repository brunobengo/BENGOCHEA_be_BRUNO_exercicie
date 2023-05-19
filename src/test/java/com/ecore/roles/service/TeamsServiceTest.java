package com.ecore.roles.service;

import com.ecore.roles.client.TeamsClient;
import com.ecore.roles.service.impl.TeamsServiceImpl;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TeamsServiceTest {

    @InjectMocks
    private TeamsServiceImpl TeamsService;
    @Mock
    private TeamsClient TeamsClient;

}
