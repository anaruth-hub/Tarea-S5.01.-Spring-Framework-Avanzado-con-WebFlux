# 🃏 Blackjack API

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-brightgreen)
![WebFlux](https://img.shields.io/badge/WebFlux-Reactive-green)
![Build](https://img.shields.io/badge/Maven-Build-red)
![Docker](https://img.shields.io/badge/Docker-Configured-blue)

Reactive API for a Blackjack game developed with **Java 21**, **Spring Boot WebFlux**, and a **hexagonal architecture**.

---

## 📖 Description

This project implements a REST API for playing Blackjack.

It allows you to:

- Create a game
- Check the game status
- Play (HIT / STAND)
- Delete a game
- Rename players
- Get player rankings

---

## 🛠 Technologies Used

- Java 21
- Spring Boot 3
- Spring WebFlux (reactive)
- Maven
- JUnit 5 + Mockito
- Swagger / OpenAPI (Springdoc)
- Docker (Dockerfile included)

---

## 🧱 Architecture

A **hexagonal architecture** has been implemented, separating responsibilities into layers:

### Domain
Pure business logic:

- Game
- Player
- Hand, Deck, Card
- GameStatus, PlayType

### Application
Use cases:

- CreateGame
- PlayGame
- GetGame
- DeleteGame
- RenamePlayer
- GetRanking

### Infrastructure
External adapters:

- REST Controllers
- In-memory repositories
- Swagger configuration
- Global Exception Handling

---

## 🌐 Main Endpoints

### 🎮 Game

- `POST /game/new`
- `GET /game/{id}`
- `POST /game/{id}/play`
- `DELETE /game/{id}/delete`

### 👤 Player

- `PUT /player/{playerId}`

### 🏆 Ranking

- `GET /ranking`

### ❤️ Health

- `GET /health`

---

## 📄 Swagger

Available at:
http://localhost:8080/swagger-ui/index.html⁠�

---

## 🚀 Running the Project

### Compile

```bash
./mvnw clean compile
On Windows:
Bash
.\mvnw.cmd clean compile
Run tests
Bash
./mvnw test
Run the application
Bash
./mvnw spring-boot:run
💾 Current Persistence
The project currently uses in-memory repositories:
Data is stored only while the application is running
Data is lost after restart
🧩 Future Improvements
The architecture is prepared to integrate:
MongoDB (for Game)
MySQL (for Player)
🏆 Ranking
The ranking is calculated using:

score = (wins * 3) + draws
⚠️ Error Handling
A GlobalExceptionHandler is implemented to return consistent JSON error responses.
🐳 Docker
Included:
Dockerfile
.dockerignore
Build
Bash
docker build -t blackjack-api .
Run
Bash
docker run -p 8080:8080 blackjack-api
Note
Docker image build could not be fully validated due to a network issue when pulling base images from Docker Hub.
🧪 Tests
Tests have been implemented for:
Domain (Game, Hand, Deck)
Services
Controllers
✔ All tests passing successfully
⚠️ Limitations / Known Issues
In-memory persistence (no real database yet)
Docker build affected by network issue (not code-related)
No authentication/security
Some endpoints lack advanced validation
Not fully production-ready responses
📊 Project Status
✔ Functional API
✔ Clean hexagonal architecture
✔ Endpoints operational
✔ Swagger working
✔ Tests passing
✔ Ranking implemented
✔ Docker configured
👩‍💻 Author

Ana Ruth