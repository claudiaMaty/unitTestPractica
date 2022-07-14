import authentication.CredentialsService;
import authentication.PermissionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import authentication.Authentication;

public class AuthenticationTest {

    // step 2 crear nuestro objeto mock
    CredentialsService credentialsServiceMock = Mockito.mock(CredentialsService.class);
    PermissionService permissionServiceMock = Mockito.mock(PermissionService.class);


    @Test
    public void verifyAuthenticationMockCRUD(){

        //step 3 configurarlo como se comporta nuestro objeto falso
        Mockito.when(credentialsServiceMock.isValidCredential("claudia","claudia")).thenReturn(true);

        Mockito.when(permissionServiceMock.getPermission("claudia")).thenReturn("CRUD");


        //step 4 utilizar el objeto falso - mock
        Authentication authentication= new Authentication();
        authentication.setCredentialsService(credentialsServiceMock);
        authentication.setPermissionService(permissionServiceMock);

        String expectedResult = "user authenticated successfully with permission: [CRUD]";
        String actualResult=authentication.login("claudia","claudia");
        Assertions.assertEquals(expectedResult,actualResult,"ERROR de verificación");

        //step 5 garantizar que utilizamos el objeto mock se necesita verificarlo

        Mockito.verify(credentialsServiceMock).isValidCredential("claudia","claudia");
        Mockito.verify(permissionServiceMock).getPermission("claudia");
    }

    @Test
    public void verifyAuthenticationMockCRU(){
        //step 3 configurarlo como se comporta nuestro objeto falso
        Mockito.when(credentialsServiceMock.isValidCredential("claudia","claudia")).thenReturn(true);
        Mockito.when(permissionServiceMock.getPermission("claudia")).thenReturn("CRU");


        //step 4 utilizar el objeto falso - mock
        Authentication authentication= new Authentication();
        authentication.setCredentialsService(credentialsServiceMock);
        authentication.setPermissionService(permissionServiceMock);

        String expectedResult = "user authenticated successfully with permission: [CRU]";
        String actualResult=authentication.login("claudia","claudia");
        Assertions.assertEquals(expectedResult,actualResult,"ERROR de verificación");

        //step 5 garantizar que utilizamos el objeto mock se necesita verificarlo

        Mockito.verify(credentialsServiceMock).isValidCredential("claudia","claudia");
        Mockito.verify(permissionServiceMock).getPermission("claudia");
    }
}
