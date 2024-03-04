from socket import *

# Definição do socket no lado do cliente
while(1):
    serverName = 'localhost'
    serverPort = 12000
    clientSocket = socket(AF_INET, SOCK_STREAM)
    clientSocket.connect((serverName,serverPort))

# Definição dos parâmetros que vão ser utilizados pela calculadora


    operation = input('Selecione a operacao:\n(1)Somar\n(2)Subtrair\n(3)Multiplicar\n(4)Dividir\n(5)Sair\n')

    if(operation == "5"):
        break


    number1 = input("Digite o primeiro número: ")
    number2 = input("Digite o segundo número: ")



    # Enviar os dados para o servidor
    clientSocket.send(operation.encode('utf-8'))
    clientSocket.send(number1.encode('utf-8'))
    clientSocket.send(number2.encode('utf-8'))

    # Receber a resposta do servidor após o cálculo
    resposta = clientSocket.recv(1024)
    respostaFinal = resposta.decode('utf-8')

    print("Resposta: ", respostaFinal, "\n\n")
    clientSocket.close()
