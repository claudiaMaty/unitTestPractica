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
    public void authenticationStaticParam(String user, String password, String permission,String expectedResult){
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
