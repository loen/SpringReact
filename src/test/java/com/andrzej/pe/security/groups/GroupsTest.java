package com.andrzej.pe.security.groups;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class GroupsTest {

    public static final String HTTP_TEST_URL = "http://test.url";
    @Mock
    Environment environment;

    private Groups groups;

    @Before
    public void setup() {
        when(environment.getProperty(Groups.STORMPATH_AUTHORIZED_ADMIN_GROUP_HREF)).thenReturn(HTTP_TEST_URL);
        groups = new Groups(environment);
    }

    @Test
    public void shouldReturnProperGroup(){
        assertEquals(HTTP_TEST_URL, groups.ADMIN);
    }


}
