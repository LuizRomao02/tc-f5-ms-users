
# 🧩 MS USERS

## 📦 Estrutura de Pacotes

```java
com.java.fiap.users
├── 📱 application             // Camada de aplicação (regras orquestradas)
│   ├── usecase               // Casos de uso (CRUDs e regras de negócio)
│   ├── dto                   // Objetos de transferência de dados
│   ├── service               // Serviços da aplicação
│   ├── exception             // Exceções específicas da aplicação
│   ├── validation            // Validações customizadas
│   └── util                  // Utilitários e helpers
│
├── 🏛️ domain                 // Camada de domínio (modelo de negócio)
│   ├── model                 // Entidades e objetos de domínio
│   └── repository            // Interfaces de persistência
│
├── ⚙️ infrastructure         // Configurações e dependências externas
│   └── config                // Beans, Security, e configurações gerais
│
└── 🖥️ presentation           // Interface com o cliente (ex: REST)
    ├── controller            // Controllers REST
    └── exception             // Tratamento global de erros
```

---

## ✅ Rodar Testes com Cobertura (JaCoCo)

```bash
mvn clean verify
```

📄 Resultado do relatório:
```plaintext
target/site/jacoco/index.html
```

Abra o arquivo `index.html` no navegador para visualizar os dados de cobertura de testes.
