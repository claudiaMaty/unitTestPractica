import authentication.Authentication;
import authentication.CredentialsService;
import authentication.PermissionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import org.junit.jupiter.api.Test;

public class AuthenticationTestParam {

    Authentication authentication;

    CredentialsService credentialsServiceMock = Mockito.mock(CredentialsService.class);
    PermissionService permissionServiceMock = Mockito.mock(PermissionService.class);

    @BeforeEach
    public void open() {
        authentication= new Authentication();

        authentication.setCredentialsService(credentialsServiceMock);
        authentication.setPermissionService(permissionServiceMock);
    }

    @Test
    public void verifyAuthenticationFourPermission(){
        Mockito.when(credentialsServiceMock.isValidCredential("admin","admin")).thenReturn(true);
        Mockito.when(permissionServiceMock.getPermission("admin")).thenReturn("CRUD");

        String expectedResult = "user authenticated successfully with permission: [CRUD]";
        String actualResult=authentication.login("admin","admin");
        Assertions.assertEquals(expectedResult,actualResult,"ERROR de verificación");

        Mockito.verify(credentialsServiceMock).isValidCredential("admin","admin");
        Mockito.verify(permissionServiceMock).getPermission("admin");

    }


    @ParameterizedTest
    @CsvSource({
            "user1, user1, CRU, user authenticated successfully with permission: [CRU]",
            "user2, user2, CRD, user authenticated successfully with permission: [CRD]",
            "user3, user3, CUD, user authenticated successfully with permission: [CUD]",
            "user4, user4, RUD, user authenticated successfully with permission: [RUD]"
    })
    public void verifyAuthenticationThreePermission(String user, String password, String permission,String expectedResult){

        Mockito.when(credentialsServiceMock.isValidCredential(user ,password)).thenReturn(true);
        Mockito.when(permissionServiceMock.getPermission(user)).thenReturn(permission);

        String actualResult=authentication.login(user ,password);
        Assertions.assertEquals(expectedResult,actualResult,"ERROR de verificación");

        Mockito.verify(credentialsServiceMock).isValidCredential(user ,password);
        Mockito.verify(permissionServiceMock).getPermission(user);
    }

    @ParameterizedTest
    @CsvSource({
            "user1, user1, CR, user authenticated successfully with permission: [CR]",
            "user2, user2, CU, user authenticated successfully with permission: [CU]",
            "user3, user3, CD, user authenticated successfully with permission: [CD]",
            "user4, user4, RU, user authenticated successfully with permission: [RU]",
            "user5, user4, RD, user authenticated successfully with permission: [RD]",
            "user6, user4, UD, user authenticated successfully with permission: [UD]"
    })
    public void verifyAuthenticationTwoPermission(String user, String password, String permission,String expectedResult){

        Mockito.when(credentialsServiceMock.isValidCredential(user ,password)).thenReturn(true);
        Mockito.when(permissionServiceMock.getPermission(user)).thenReturn(permission);

        String actualResult=authentication.login(user ,password);
        Assertions.assertEquals(expectedResult,actualResult,"ERROR de verificación");

        Mockito.verify(credentialsServiceMock).isValidCredential(user ,password);
        Mockito.verify(permissionServiceMock).getPermission(user);
    }

    @ParameterizedTest
    @CsvSource({
            "user1, user1, C, user authenticated successfully with permission: [C]",
            "user2, user2, R, user authenticated successfully with permission: [R]",
            "user3, user3, U, user authenticated successfully with permission: [U]",
            "user4, user4, D, user authenticated successfully with permission: [D]"
    })
    public void verifyAuthenticationOnePermission(String user, String password, String permission,String expectedResult){

        Mockito.when(credentialsServiceMock.isValidCredential(user ,password)).thenReturn(true);
        Mockito.when(permissionServiceMock.getPermission(user)).thenReturn(permission);

        String actualResult=authentication.login(user ,password);
        Assertions.assertEquals(expectedResult,actualResult,"ERROR de verificación");

        Mockito.verify(credentialsServiceMock).isValidCredential(user ,password);
        Mockito.verify(permissionServiceMock).getPermission(user);
    }
}
