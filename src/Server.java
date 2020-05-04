import EchoApp.Echo;
import EchoApp.EchoHelper;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

public class Server {

    public static void main(String args[]) {
        try {
            // Instancia um ORB
            ORB orb = ORB.init(args, null);

            // Pega a referência do POA raiz e ativa o POAManager
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // Cria o servidor
            EchoServer server = new EchoServer();

            // Conecta o servidor ao ORB e o coloca como disponível
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(server);
            Echo href = EchoHelper.narrow(ref);

            // Identifica o serviço "NameService" com o servidor no ORB
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // Converte uma string de nome para o equivalente em uma array de
            // Name Components
            NameComponent path[] = ncRef.to_name("ECHO-SERVER");
            // Da bind entre o serviço aberto no servidor e o nome identificador,
            // agora é possívle acessar o serviço nesse endereço
            ncRef.rebind(path, href);

            System.out.println("Server ready and waiting ...");

            // Roda o ORB e espera uma conexão
            orb.run();
        }

        catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("Exiting ...");

    }
}
