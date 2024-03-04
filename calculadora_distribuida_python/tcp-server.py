from socket import *
import time

# Métodos da calculadora
def somar(x, y):
     return x+y
def subtrair(x, y):
     return x-y
def multiplicar(x, y):
     return x*y
def dividir(x, y):
     return x/y

# Definição do socket no lado do servidor
serverPort = 12000
serverSocket = socket(AF_INET,SOCK_STREAM)
serverSocket.bind(('',serverPort))
serverSocket.listen(1)
print("The server is ready to receive!")     # Confirmação do funcionamento do servidor

# Manter o servidor ligado
while 1:

     # Estabelecer conexão com quem tentar se conectar
     connectionSocket, addr = serverSocket.accept()
     
     try:

          # Receber os dados do cliente
          operator = connectionSocket.recv(1024).decode("utf-8")
          num1 = connectionSocket.recv(1024).decode("utf-8")
          num2 = connectionSocket.recv(1024).decode("utf-8")

          # Escolher operação baseado no operador desejado
          if(operator == "1"):
               connectionSocket.send(str(somar(float(num1), float(num2))).encode("utf-8"))
          elif(operator == "2"):
               connectionSocket.send(str(subtrair(float(num1), float(num2))).encode("utf-8"))
          elif(operator == "3"):
               connectionSocket.send(str(multiplicar(float(num1), float(num2))).encode("utf-8"))
          elif(operator == "4"):
               if(num2 == "0"):
                    connectionSocket.send("Divisão por 0, tente novamente com denominador válido!".encode("utf-8"))
               else:
                    connectionSocket.send(str(dividir(float(num1), float(num2))).encode("utf-8"))

     except:

          # Problema no tratamento de dados
          connectionSocket.send("Data error!")
     connectionSocket.close()
