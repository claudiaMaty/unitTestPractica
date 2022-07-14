import authenticationStatic.Authentication;
import authenticationStatic.CredentialsStaticService;
import authenticationStatic.PermissionStaticService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockedStatic;

public class AuthenticationStaticTest {

    @Test
    public void authenticationStatic(){
        MockedStatic<CredentialsStaticService> objectCredentialMocked= Mockito.mockStatic(CredentialsStaticService.class);
        MockedStatic<PermissionStaticService> objectPermissionMocked= Mockito.mockStatic(PermissionStaticService.class);

        objectCredentialMocked.when(()->CredentialsStaticService.isValidCredential("admin","admin")).thenReturn(true);
        objectPermissionMocked.when(()->PermissionStaticService.getPermission("admin")).thenReturn("CRUD");

        Authentication authenticationStatic= new Authentication();

        String expectedResult = "user authenticated successfully with permission: [CRUD]";
        String actualResult= authenticationStatic.login("admin","admin");

        Assertions.assertEquals(expectedResult,actualResult,"ERROR el factorial fue incorrecto");

        // evitar problemas de creacion sobre el mismo metodo estatico
        objectCredentialMocked.close();
        objectPermissionMocked.close();

    }
}
