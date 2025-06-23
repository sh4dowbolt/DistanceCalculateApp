# DistanceCalculateApp

## Description
Project is REST API for calculating distance between two points on the map using coordinates from different geocoding services (Yandex Maps and DaData). The application allows getting distance in different units of measurement (kilometers, meters, centimeters).

## Stack
- **Programming language**: Java 17
- **Framework**: Spring Boot 3.5.0
- **Database**: MySQL 8.0
- **Project management**: Maven
- **Extra tools**:
  - **Spring WebClient** (for external API calls)
  - **Spring Data JPA** (for database operations)
  - **Docker** (for containerization)
  - **Actuator** (for monitoring)

## Setup
### Requirements
- Java 17 or higher
- Docker and Docker Compose
- Maven
- Access to Yandex Maps API
- Access to DaData API

### Launch project
1. Clone repository:
   ```bash
   git clone https://github.com/sh4dowbolt/DistanceCalculateApp.git
   cd <project directory>
   mvn clean package
   ```
2. Launch docker-compose project:
   ```bash 
   docker-compose up -d
   ```
3. That's all. App will be available on port 8080

## Design decisions
1. **Geocoding**:
   - Using two different geocoding services (Yandex Maps and DaData) for better accuracy
   - Comparing results from both services to ensure data consistency

2. **Distance calculation**:
   - Calculating distance between coordinates using the Haversine formula
   - Supporting different units of measurement (KM, M, CENTIMETER)
   - Results are stored in the database for history

3. **Error handling**:
   - Comprehensive error handling for API calls
   - Validation of input data
   - Proper error responses with meaningful messages

4. **Monitoring**:
   - Health checks for application and database
   - Metrics available through Actuator
   - Docker container health monitoring

## Example of requests
- **Calculate distance**:
  ```http
  POST /getDistance
  Content-Type: application/json
  
  ["Москва, Красная площадь, 1"]
  ```
  Query parameters:
  - `measureType` (optional): KM, M, CENTIMETER (default: KM)

  Response:
  ```json
  {
      "difference": 0.0,
      "measureType": "KM"
  }
  ```

## Documentation
Actuator endpoints available at:
- Health check: `http://localhost:8080/actuator/health`
- Metrics: `http://localhost:8080/actuator/metrics`

## Configuration
The project already has configured environment variables, but you can modify them according to your needs. Update the existing `.env` file in the root directory with the following content:
```env
# MySQL Configuration
MYSQL_ROOT_PASSWORD=root_password
MYSQL_DATABASE=db
MYSQL_USER=user
MYSQL_PASSWORD=password

# API Keys
DADATA_API_KEY=dadata_api_key
DADATA_SECRET_KEY=dadata_secret_key
YANDEX_API_KEY=yandex_api_key
```

## Error handling
The application returns the following HTTP statuses:
- 200: Successful request
- 400: Invalid request format
- 404: Resource not found
- 500: Internal server error

Example error response:
```json
{
    "timestamp": "2024-03-16T22:39:27.168Z",
    "status": 400,
    "error": "Bad Request",
    "message": "Invalid address format",
    "path": "/getDistance"
}
```
