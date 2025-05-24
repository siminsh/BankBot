# Banking Chatbot

An AI-powered banking chatbot built with Spring Boot that provides intelligent customer service for banking operations.

## 🚀 Features

- **AI-Powered Conversations**: Integration with OpenAI GPT for natural language processing
- **Banking Operations**: Handle common banking queries and transactions
- **Event-Driven Architecture**: Uses Apache Kafka for asynchronous message processing
- **Persistent Storage**: PostgreSQL database for data persistence
- **RESTful API**: Web-based endpoints for chatbot interactions
- **Dockerized Deployment**: Easy deployment with Docker Compose

## 🛠️ Technology Stack

- **Backend Framework**: Spring Boot 3.1.5
- **Language**: Java 17
- **Database**: PostgreSQL
- **Message Broker**: Apache Kafka
- **AI Integration**: OpenAI GPT-3/4 API
- **Build Tool**: Maven
- **Containerization**: Docker & Docker Compose

## 📋 Prerequisites

Before running this application, make sure you have:

- Java 17 or higher
- Maven 3.6+
- Docker and Docker Compose
- OpenAI API key

## 🔧 Setup and Installation

### 1. Clone the Repository
```bash
git clone <repository-url>
cd bankbot
```

### 2. Environment Configuration
Create a `.env` file in the root directory with your OpenAI API key:
```env
OPENAI_API_KEY=your_openai_api_key_here
```

### 3. Start Infrastructure Services
Start PostgreSQL, Zookeeper, and Kafka using Docker Compose:
```bash
docker-compose up -d
```

### 4. Build the Application
```bash
mvn clean install
```

### 5. Run the Application
```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

## 🐳 Docker Deployment

To run the entire application stack with Docker:

```bash
# Start all services
docker-compose up -d

# View logs
docker-compose logs -f

# Stop services
docker-compose down
```

## 📚 API Documentation

### Chat Endpoint
- **POST** `/api/chat`
  - Send a message to the chatbot
  - Request body: `{"message": "What's my account balance?"}`
  - Response: `{"response": "I can help you check your account balance..."}`

### Health Check
- **GET** `/actuator/health`
  - Check application health status

## 🏗️ Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── bankbot/
│   │           ├── application/          # Application services
│   │           ├── controller/           # REST controllers
│   │           ├── domain/              # Domain models and logic
│   │           ├── infrastructure/      # External integrations
│   │           ├── model/              # Data models
│   │           └── BankbotApplication.java
│   └── resources/
│       └── application.yml             # Configuration
└── test/                               # Unit and integration tests
```

## ⚙️ Configuration

Key configuration properties in `application.yml`:

```yaml
server:
  port: 8080

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/bankbot
    username: postgres
    password: postgres
  
  kafka:
    bootstrap-servers: localhost:9092
    consumer:
      group-id: bankbot-group
```

## 🔒 Security Considerations

- Store sensitive API keys in environment variables
- Implement proper authentication and authorization
- Validate and sanitize all user inputs
- Use HTTPS in production environments
- Regularly update dependencies for security patches

## 🧪 Testing

Run the test suite:
```bash
# Unit tests
mvn test

# Integration tests
mvn verify
```

## 📈 Monitoring

The application includes:
- Spring Boot Actuator endpoints for health monitoring
- Kafka consumer/producer metrics
- Database connection pool monitoring

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📝 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🆘 Troubleshooting

### Common Issues

**Database Connection Error**
- Ensure PostgreSQL container is running: `docker-compose ps`
- Check database credentials in configuration

**Kafka Connection Error**
- Verify Kafka and Zookeeper containers are healthy
- Check port availability (9092 for Kafka, 2181 for Zookeeper)

**OpenAI API Error**
- Verify your API key is correctly set in environment variables
- Check your OpenAI account has sufficient credits

### Logs
View application logs:
```bash
# Application logs
mvn spring-boot:run

# Docker container logs
docker-compose logs bankbot
```

## 📞 Support

For questions or support, please open an issue in the repository or contact the development team.

---

**Note**: This is a development version. Please ensure proper security measures and testing before deploying to production.
