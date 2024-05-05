# Sistema de checkout via Pix - Case Bradesco
Projeto case visa apresentar diagrama e breve implementacao de um servico de checkout via pix de uma loja virtual.

Requisitos funcionais
1. O sistema deverá receber da loja através da API uma requisição de pagamento por PIX
   com os dados de um pedido.
2. O sistema deverá enviar uma requisição de pagamento ao sistema de transferência PIX
   de acordo com os dados do pedido para recebimento de um QR Code para pagamento.
3. O sistema deverá retornar para a loja os dados recebidos para a geração de um QR
   Code para pagamento do pedido de forma síncrona através da API.
4. O sistema deverá receber do sistema de transferência PIX o status de pagamento de
   um pedido através da API de forma assíncrona e notificar a loja.
5. O sistema deverá registrar os dados do pedido e status de pagamento das transações
   em base de dados para controle, conciliação e consulta de todas as transações
   realizadas.

Requisitos não funcionais
1. A comunicação entre as APIs será por mensagens JSON sobre o protocolo HTTPS e
   autenticadas.
2. A disponibilidade do sistema deverá ser de 24/7.
3. Será utilizado um gerenciador de base de dados SQL Server.
4. Os módulos de software serão executados em servidores de aplicação WebSphere
   Application Server 8.5.5 on premise.

Questoes

1. Diagrama Arquitetura de Softaware Alto Nivel

   - Diagrama da Arquitetura: https://drive.google.com/file/d/1taqMtTDXF0abMc06cw2cUSB4sxrgPNyt/view?usp=sharing
   - Diagrama de Clase: https://drive.google.com/drive/u/0/folders/1twrve2HZU2R6PH6v_rMalTJgHoBGKQG3
   - Diretorio drive: https://drive.google.com/file/d/1EBy-Z2nbSE5uLoxUAr_w53RWfHT9MO2Y/view?usp=sharing

2. Componentes de software do sistema de integração e a tecnologia adotada
   * Lingaguem: java 17
   * Framework: springboot 3.2.5
   * Ferramenta de gerenciamento: maven/pom.xml
   * Ferramenta de Containerizacao: docker-compose
   * Banco de Dados: SQL Serve/Azure-sql-edge(compativel com MACOS M1-ARM)
   * Servidor de Registro: Spring Cloud Eureka
   * Projetos: Spring Web, Spring Data JPA
<<<<<<< Updated upstream
   * Documentacao: Open-API
=======
   * Mensageria: RabbitMQ
   * Documentacao: Open-API(Swagger)- http://localhost:8000/checkout/swagger-ui.html
>>>>>>> Stashed changes
