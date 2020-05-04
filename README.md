
# TRABALHO DE CORBA

  

## Instructions

  

### 1. Escrever um arquivo IDL

O arquivo IDL define a interface que será usada pelo cliente e servidor para se comunicarem e trocar objetos.

Quando o .idl for compildo, vários arquivos serão gerados, os stubs e skeletons

* O stub é utilizado pelo cliente para se comunicar com o servidor

* O skeleton é utilizado pelo servidor para se comunicar com o cliente

* Ambos se comunicam com o servidor ORB afim de facilitar a execução remota

  

O módulo no arquivo IDL será o pacote e o diretório no qual o código em Java será gerado

  

HelloWorld.idl

```IDL

module HelloWorld {

interface HelloWorld {

string helloWorldString();

};

};

```

  

### 2. Gerando o código do stub e skeleton

Junto no pacote JDK, existe um programa idlj que gera automáticamente os códigos do stub e do skeleton.

Entre na pasta do seu arquivo .idl e digite

  

```shell

idlj –fall HelloWorld.idl

```

Diversos arquivos serão criados dentro da pasta EchoApp:

* _EchoStub.java

* Echo.java

* EchoHelper.java

* EchoHolder.java

* EchoOperations.java

* EchoPOA.java

  

### 3. Escrevendo o código do servidor

O arquivo servidor vai ser herdeiro da classe EchoPOA que foi gerada pelo idlj.

A classe EchoPOA implementa a interface de "EchoOperations"

* Essa interface contém os métodos que foram definidos no arquivo HelloWorld.idl, mas padronizados para Java.

  
  

Criado então o [EchoServer.java](src/EchoServer.java), uma classe que herda a classe abstrata EchoPoa e então implementa os métodos contidos nela.

  

Se cria também o método main no arquivo [Server.java](src/Server.java) para se comunicar com o object request broker (ORB), registrando o servidor com o ORB para que clientes posssam encontra-lo.

  

### 4. Escrevendo o código do cliente

O arquivo do cliente [Client.java](src/Client.java) adquire uma referência ao ORB, e recebe então o nome do objeto servidor que ele quer invocar.

* Nesse caso é o ECHO-SERVER

  

Depois de conseguir a instância do objeto servidor do servidor propriamente dito, é possivel chamar métodos nele como se estivesse na própria máquina virtual Java

  

### 5. Compilando o código

1. Compile o stub e o skeleton da pasta criada pelo idlj.

  

Windows

```shell

javac EchoApp\*.java

```

Linux

```shell

javac EchoApp/*.java

```

2. Gere um arquivo JAR do stub e do skeleton compilados.

Windows

```shell

jar cvf echoapp.jar EchoApp\*.class

```

Linux

```shell

jar cvf echoapp.jar EchoApp/*.class

```

  

3. Compile as classes de cliente e servidor.

Windows

```shell

javac -classpath .;echoapp.jar Server.java EchoServer.java Client.java

```

Linux

```shell

javac -classpath .:echoapp.jar Server.java EchoServer.java Client.java

```

  

### 6. Rodando a aplicação

1. Inicie o servidor ORB. (Nesse caso localhost porta 1050)

```shell

orbd -ORBInitialPort 1050 -ORBInitialHost localhost

```

2. Inicie o servidor conectando no ORB aberto.

```shell

java Server -ORBInitialPort 1050 -ORBInitialHost localhost

```

  

3. Inicie o cliente conectando o no mesmo ORB

```shell

java Client -ORBInitialPort 1050 -ORBInitialHost localhost

```
As informações para conectar no servidor estão no código no cliente.
  
Se tudo compilou corretamente, a saída deve ser:

```shell

Hello World!!!!!!!

```