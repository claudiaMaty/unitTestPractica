import authentication.CredentialsService;
import authentication.PermissionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import authentication.Authentication;

public class AuthenticationTest {

    // step 2 crear nuestro objeto mock
    CredentialsService credentialsServiceMock = Mockito.mock(CredentialsService.class);
    PermissionService permissionServiceMock = Mockito.mock(PermissionService.class);


    @Test
    public void authenticationMock(){

        //step 3 configurarlo como se comporta nuestro objeto falso
        Mockito.when(credentialsServiceMock.isValidCredential("admin","admin")).thenReturn(true);
        Mockito.when(permissionServiceMock.getPermission("admin")).thenReturn("CRUD");

        //step 4 utilizar el objeto falso - mock
        Authentication authentication= new Authentication();
        authentication.setCredentialsService(credentialsServiceMock);
        authentication.setPermissionService(permissionServiceMock);

        String expectedResult = "user authenticated successfully with permission: [CRUD]";
        String actualResult=authentication.login("admin","admin");
        Assertions.assertEquals(expectedResult,actualResult,"ERROR de verificación");

        //step 5 garantizar que utilizamos el objeto mock se necesita verificarlo

        Mockito.verify(credentialsServiceMock).isValidCredential("admin","admin");
        Mockito.verify(permissionServiceMock).getPermission("admin");
    }
}
