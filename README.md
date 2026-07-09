# ✈️ Airline Flight Service

A comprehensive **Spring Boot REST API** for managing airline flight operations, airplane inventory, airport logistics, and seat management. This is a production-ready backend service demonstrating enterprise-level Java development practices and architectural patterns.


## 🎯 Overview

**Airline Flight Service** is a backend microservice built with Spring Boot that handles the complete lifecycle of flight operations. It demonstrates modern Java development practices including:

- RESTful API design principles
- Multi-layered architecture (Controller → Service → Repository)
- Object-relational mapping with JPA/Hibernate
- Database schema versioning with Flyway migrations
- Input validation and error handling
- Lombok for reducing boilerplate code
- Spring Data JPA with advanced query specifications

This project is ideal for managing airlines' core operations including flight scheduling, airplane management, seat allocations, and airport logistics.

---

## ✨ Key Features

### Flight Management
- Create, read, update, and delete flight schedules
- Track flight status (SCHEDULED, DEPARTED, LANDED, CANCELLED)
- Manage departure and arrival times
- Associate flights with specific airplanes and airports

### Airplane Management
- Maintain airplane inventory
- Track airplane capacity and configuration
- Manage airplane seat allocations
- Seat availability tracking

### Airport & City Management
- Store airport information and details
- Manage city location data
- Track airport codes and locations

### Seat Management
- Track individual airplane seats
- Monitor seat occupancy status
- Manage seat assignments
- Support for different seat classes (Economy, Business, First Class - extensible)

### Advanced Features
- **Flyway Database Migrations**: Version control for database schema
- **Spring Data JPA Specifications**: Advanced querying capabilities
- **Input Validation**: Using Spring Validation annotations
- **Exception Handling**: Custom exception handling layer
- **DTO Pattern**: Clean separation between internal and external representations
- **Mapper Layer**: Entity-to-DTO conversions

---

## 🛠️ Technology Stack

### Core Technologies
| Component | Technology | Version |
|-----------|-----------|---------|
| **Language** | Java | 21 LTS |
| **Framework** | Spring Boot | 3.5.4 |
| **Build Tool** | Maven | 3.6+ |
| **Database** | PostgreSQL | 12+ |
| **ORM** | Hibernate/JPA | Latest (via Spring Boot) |

### Key Dependencies
| Dependency | Purpose |
|-----------|---------|
| **Spring Boot Starter Web** | RESTful API development |
| **Spring Data JPA** | Database abstraction and ORM |
| **Spring Boot Starter Validation** | Input validation using annotations |
| **PostgreSQL Driver** | Database connectivity |
| **Flyway** | Database schema versioning and migrations |
| **Lombok** | Reduce boilerplate code (getters, setters, constructors) |
| **Spring Boot DevTools** | Hot reload during development |

---

## 📂 Project Structure

### Multi-Layered Architecture

```
airline_flight_service/
│
├── src/
│   ├── main/
│   │   ├── java/com/airline/flight/
│   │   │   ├── FlightApplication.java              ⭐ Entry Point
│   │   │   │
│   │   │   ├── controllers/
│   │   │   │   ├── HomeController.java             🎯 Home endpoint
│   │   │   │   └── v1/
│   │   │   │       ├── FlightController.java       🎯 Flight endpoints
│   │   │   │       ├── AirplaneController.java     🎯 Airplane endpoints
│   │   │   │       ├── AirportController.java      🎯 Airport endpoints
│   │   │   │       ├── CityController.java         🎯 City endpoints
│   │   │   │       └── AirplaneSeatController.java 🎯 Seat endpoints
│   │   │   │
│   │   │   ├── services/
│   │   │   │   ├── FlightService.java              💼 Flight business logic
│   │   │   │   ├── AirplaneService.java            💼 Airplane business logic
│   │   │   │   ├── AirportService.java             💼 Airport business logic
│   │   │   │   ├── CityService.java                💼 City business logic
│   │   │   │   ├── AirplaneSeatService.java        💼 Seat business logic
│   │   │   │   └── implementation/
│   │   │   │       ├── FlightServiceImpl.java       💼 Flight implementation
│   │   │   │       ├── AirplaneServiceImpl.java     💼 Airplane implementation
│   │   │   │       ├── AirportServiceImpl.java      💼 Airport implementation
│   │   │   │       ├── CityServiceImpl.java         💼 City implementation
│   │   │   │       └── AirplaneSeatServiceImpl.java 💼 Seat implementation
│   │   │   │
│   │   │   ├── entity/
│   │   │   │   ├── Airplane.java                   📦 Airplane entity
│   │   │   │   ├── Flight.java                     📦 Flight entity
│   │   │   │   ├── Airport.java                    📦 Airport entity
│   │   │   │   ├── City.java                       📦 City entity
│   │   │   │   └── AirplaneSeat.java               📦 Seat entity
│   │   │   │
│   │   │   ├── dto/
│   │   │   │   ├── FlightDTO.java                  📄 Flight DTO
│   │   │   │   ├── AirplaneDTO.java                📄 Airplane DTO
│   │   │   │   ├── AirportDTO.java                 📄 Airport DTO
│   │   │   │   ├── CityDTO.java                    📄 City DTO
│   │   │   │   └── AirplaneSeatDTO.java            📄 Seat DTO
│   │   │   │
│   │   │   ├── respositories/
│   │   │   │   ├── FlightRepository.java           🗂️ Flight repository
│   │   │   │   ├── AirplaneRepository.java         🗂️ Airplane repository
│   │   │   │   ├── AirportRepository.java          🗂️ Airport repository
│   │   │   │   ├── CityRepository.java             🗂️ City repository
│   │   │   │   └── AirplaneSeatRepository.java     🗂️ Seat repository
│   │   │   │
│   │   │   ├── mapper/
│   │   │   │   ├── FlightMapper.java               🔄 Flight mapper
│   │   │   │   ├── AirplaneMapper.java             🔄 Airplane mapper
│   │   │   │   ├── AirportMapper.java              🔄 Airport mapper
│   │   │   │   ├── CityMapper.java                 🔄 City mapper
│   │   │   │   └── AirplaneSeatMapper.java         🔄 Seat mapper
│   │   │   │
│   │   │   ├── specification/
│   │   │   │   ├── FlightSpecification.java        🔍 Flight query spec
│   │   │   │   ├── AirplaneSpecification.java      🔍 Airplane query spec
│   │   │   │   └── ...                             🔍 Other specs
│   │   │   │
│   │   │   ├── enums/
│   │   │   │   ├── FlightStatus.java               🏷️ Flight status enum
│   │   │   │   ├── SeatClass.java                  🏷️ Seat class enum
│   │   │   │   └── SeatStatus.java                 🏷️ Seat status enum
│   │   │   │
│   │   │   ├── exception/
│   │   │   │   ├── ResourceNotFoundException.java   ⚠️ Custom exception
│   │   │   │   ├── InvalidInputException.java       ⚠️ Custom exception
│   │   │   │   ├── GlobalExceptionHandler.java      ⚠️ Exception handler
│   │   │   │   └── ErrorResponse.java               ⚠️ Error response DTO
│   │   │   │
│   │   │   └── constants/
│   │   │       ├── ApiRoutes.java                🔧 Application Route Resouces constants
│   │   │
│   │   └── resources/
│   │       ├── application.properties               ⚙️ Application config
│   │       └── db/
│   │           ├── Test.sql                         📊 Test queries
│   │           └── migration/
│   │               ├── V1__Create_airplane_table.sql
│   │               ├── V2__Create_cities_table.sql
│   │               ├── V3__Create_airport_table.sql
│   │               ├── V4__Create_flight_table.sql
│   │               ├── V5__convert_flight_status_to_enum.sql
│   │               └── V6__Create_airplane_seat_table.sql
│   │
│   └── test/
│       └── java/com/airline/flight/
│           ├── FlightApplicationTests.java
│           └── ... (unit & integration tests)
│
├── pom.xml                                          📋 Maven configuration
├── mvnw                                             📝 Maven wrapper (Unix)
├── mvnw.cmd                                         📝 Maven wrapper (Windows)
├── .gitignore                                       🚫 Git ignore rules
├── README.md                                        📖 This file
└── target/                                          📦 Compiled artifacts

```

🏆 Best Practices Implemented
1. Clean Code Architecture
- ✅ Multi-layered architecture (Controller → Service → Repository → Entity)
- ✅ Separation of concerns
- ✅ Single Responsibility Principle (SRP)
- ✅ Dependency Injection and IoC Container
2. Database Best Practices
- ✅ JPA/Hibernate ORM for type-safe database access
- ✅ Flyway for version-controlled schema migrations
- ✅ Connection pooling (HikariCP)
- ✅ Proper indexing and relationships
- ✅ Transactional integrity with @Transactional
3. REST API Design
- ✅ RESTful endpoint design
- ✅ Proper HTTP methods (GET, POST, PUT, DELETE)
- ✅ Standard HTTP status codes
- ✅ Consistent response format
- ✅ DTO for API contracts
- ✅ Request/response validation
4. Code Quality
- ✅ Lombok for reducing boilerplate (getters, setters, constructors)
- ✅ Builder pattern for object construction
- ✅ Spring validation annotations
- ✅ Custom exception handling
- ✅ Global exception handler with @ControllerAdvice
5. Version Control
- ✅ Flyway versioned migrations
- ✅ Git-friendly project structure
- ✅ Proper .gitignore configuration
