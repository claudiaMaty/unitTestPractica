import authenticationStatic.Authentication;
import authenticationStatic.CredentialsStaticService;
import authenticationStatic.PermissionStaticService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class AuthenticationStaticTestParam {

    @ParameterizedTest
    @CsvSource({
            "admin, admin, CRUD, user authenticated successfully with permission: [CRUD]",
            "user1, user1, CRU, user authenticated successfully with permission: [CRU]",
            "user2, user2, CRD, user authenticated successfully with permission: [CRD]",
            "user3, user3, CUD, user authenticated successfully with permission: [CUD]",
            "user4, user4, RUD, user authenticated successfully with permission: [RUD]",
            "user5, user5, CR, user authenticated successfully with permission: [CR]",
            "user6, user6, CU, user authenticated successfully with permission: [CU]",
            "user7, user7, CD, user authenticated successfully with permission: [CD]",
            "user8, user8, RU, user authenticated successfully with permission: [RU]",
            "user9, user9, RD, user authenticated successfully with permission: [RD]",
            "user10, user10, UD, user authenticated successfully with permission: [UD]",
            "user11, user11, C, user authenticated successfully with permission: [C]",
            "user12, user12, R, user authenticated successfully with permission: [R]",
            "user13, user13, U, user authenticated successfully with permission: [U]",
            "user14, user14, D, user authenticated successfully with permission: [D]"
    })
    public void verifyAuthenticationStaticParam(String user, String password, String permission,String expectedResult){
        MockedStatic<CredentialsStaticService> objectCredentialMocked= Mockito.mockStatic(CredentialsStaticService.class);
        MockedStatic<PermissionStaticService> objectPermissionMocked= Mockito.mockStatic(PermissionStaticService.class);

        objectCredentialMocked.when(()->CredentialsStaticService.isValidCredential(user,password)).thenReturn(true);
        objectPermissionMocked.when(()->PermissionStaticService.getPermission(user)).thenReturn(permission);

        Authentication authenticationStatic= new Authentication();

        String actualResult= authenticationStatic.login(user,password);

        Assertions.assertEquals(expectedResult,actualResult,"ERROR el factorial fue incorrecto");

        // evitar problemas de creacion sobre el mismo metodo estatico
        objectCredentialMocked.close();
        objectPermissionMocked.close();

    }
}
