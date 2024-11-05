# Mindly

![Tela inicial do Mindly](./frontend//src/assets/Tela1.svg)

Este é um projeto de agenda desenvolvido com React. O aplicativo foi projetado para ser acessível, simples e acolhedor.

## Mapa de Navegação

- **Navegação Inicial**:

1.	Página inicial com menu na barra; 
2.	Tela para escolha de perfil (profissional ou paciente); 
3.	Formulários de cadastro de acordo com o perfil escolhido; 
4.	Login com validação de perfil; 

- **Perfil paciente. Telas**:

5.	Ver perfil dos profissionais (bio); 
6.	Ver grade de horário disponível do profissional escolhido e agendar e desmarcar;  AGENDAR, FALTA DESMARCAR

- **Perfil profissional. Telas**: 

7.	Perfil profissional com informações para o paciente visualizar; INICIADO
8.	Lista pacientes;
9.	Informação do paciente (medicação, data das sessões e qntd de sessões);
10.	Adicionar e remover grades de horários. Confirmação de agendamento;
11.	Ver pacientes agendados. Remarcação;
12.	Adicionar link de chamada de vídeo ao agendamento;
13.   Buscar Profissional. 

## Tecnologias Utilizadas

- **Vite**: Framework de construção de projetos de frontend que se destina a oferecer uma experiência de desenvolvimento mais rápida e leve para projetos de web modernos.
- **Java Spring-Boot**: Framework de software livre, para criar aplicativos independentes de nível de produção que são executados na Java Virtual Machine (JVM).
-- **MySQL**: Sistema de gerenciamento de banco de dados (SGBD) de código aberto, que permite a criação, modificação e consulta de dados.

### Versões

* Node - 20.17.0
* npm - 10.9.0

### Dependências

```yaml
    "@testing-library/jest-dom": "^5.17.0",
    "@testing-library/react": "^13.4.0",
    "@testing-library/user-event": "^13.5.0",
    "react": "^18.3.1",
    "react-dom": "^18.3.1",
    "react-scripts": "5.0.1",
    "react-hook-form": "^7.53.1",
    "react-router-dom": "^6.27.0",
    "web-vitals": "^2.1.4"
```

## Instalação

1. Clone o repositório:

   ```bash
   git clone https://github.com/Italo-Yan/Mindly
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd Mindly
   ```

3. Instale as dependências do projeto:

   ```bash
   npm install
   ```

4. Instale as dependências do frontend:

   ```bash
   npm install
   ```   

   ```bash
   cd frontend
   ```   

   ```bash
   npm install
   ```   

5. Execute o projeto:

   ```bash
   cd ..
   ```

   ```bash
   npm start
   ```
