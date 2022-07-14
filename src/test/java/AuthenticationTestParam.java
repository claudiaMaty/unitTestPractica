import authentication.Authentication;
import authentication.CredentialsService;
import authentication.PermissionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

public class AuthenticationTestParam {

    Authentication authentication = new Authentication();

    CredentialsService credentialsServiceMock = Mockito.mock(CredentialsService.class);
    PermissionService permissionServiceMock = Mockito.mock(PermissionService.class);

    @ParameterizedTest
    @CsvSource({
            "admin, admin, CRUD, user authenticated successfully with permission: [CRUD]",
            "admin1, admin1, CRU, user authenticated successfully with permission: [CRU]",
            "admin2, admin2, CRD, user authenticated successfully with permission: [CRD]",
            "admin3, admin3, CUD, user authenticated successfully with permission: [CUD]",
            "admin4, admin4, RUD, user authenticated successfully with permission: [RUD]",
            "admin5, admin5, CR, user authenticated successfully with permission: [CR]",
            "admin6, admin6, CU, user authenticated successfully with permission: [CU]",
            "admin7, admin7, CD, user authenticated successfully with permission: [CD]",
            "admin8, admin8, RU, user authenticated successfully with permission: [RU]",
            "admin9, admin9, RD, user authenticated successfully with permission: [RD]",
            "admin10, admin10, UD, user authenticated successfully with permission: [UD]",
            "admin11, admin11, C, user authenticated successfully with permission: [C]",
            "admin12, admin12, R, user authenticated successfully with permission: [R]",
            "admin13, admin13, U, user authenticated successfully with permission: [U]",
            "admin14, admin14, D, user authenticated successfully with permission: [D]"
    })
    public void authenticationParam(String user, String password, String permission,String expectedResult){

        Mockito.when(credentialsServiceMock.isValidCredential(user ,password)).thenReturn(true);
        Mockito.when(permissionServiceMock.getPermission(user)).thenReturn(permission);

        authentication.setCredentialsService(credentialsServiceMock);
        authentication.setPermissionService(permissionServiceMock);

        String actualResult=authentication.login(user ,password);
        Assertions.assertEquals(expectedResult,actualResult,"ERROR de verificación");

        Mockito.verify(credentialsServiceMock).isValidCredential(user ,password);
        Mockito.verify(permissionServiceMock).getPermission(user);
    }

}
