# user-service

Para executar este serviço, basta executar como uma aplicação spring boot, o projeto roda por padrão na porta 9091 e existem 3 enpoints disponíveis, sendo eles: (GET) http://localhost:9092/api/users/ - retorna a lista de usuários cadastrados na base. Necessário autenticação. (POST) http://localhost:9092/api/users/register - para cadastrar um novo usuário na base. Sem autenticação (PUT) http://localhost:9092/api/users/{id} - para fazer o update do email do usuário, onde é necessário passar um ID e todas as informações do usuário(body igual ao do register);
