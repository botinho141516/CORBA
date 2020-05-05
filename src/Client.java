import EchoApp.Echo;
import EchoApp.EchoHelper;
import org.omg.CORBA.ORB;
import org.omg.CORBA.ORBPackage.InvalidName;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.NotFound;

public class Client {

    public static void main(String args[]) {
        try {
            // Instancia um ORB
            ORB orb = ORB.init(args, null);

            // Identifica o serviço "NameService" com o servidor no ORB
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Importa todas as funções relativas ao serviço com o nome "ECHO-SERVER"
            Echo href = EchoHelper.narrow(ncRef.resolve_str("ECHO-SERVER"));

            // Executa a função instanciada no servidor
            String hello = href.helloWorldString();
            System.out.println(hello);
            int fibonacciValue = href.Fibonacci(43);
            System.out.println(fibonacciValue);

            // Lida com exceções
        } catch (InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (CannotProceed cannotProceed) {
            cannotProceed.printStackTrace();
        } catch (org.omg.CosNaming.NamingContextPackage.InvalidName invalidName) {
            invalidName.printStackTrace();
        } catch (NotFound notFound) {
            notFound.printStackTrace();
        }

    }

}
