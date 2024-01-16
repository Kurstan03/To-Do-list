# To-Do list
This project is for task list management.

## Run
1. **Clone the repository:**
   
   ```bash
   git clone https://github.com/Kurstan03/To-Do-list.git
   
3. **Navigate to the project directory:**
    ```bash
   cd To-Do-list
   
4. **Database Configuration:**
   - Make sure to configure your database connection parameters in the `application.properties`
    ```bash
   spring.datasource.url=${DATASOURCE_URL}
   spring.datasource.username=${DATASOURCE_USERNAME}
   spring.datasource.password=${DATASOURCE_PASSWORD}

5. **Build and run the project using Maven:**
    ```bash
   mvn clean install
   java -jar target/To-Do-list-0.0.1-SNAPSHOT.jar
   
6. **Check functionality:**
   - Open a web browser and go to - http://localhost:8080
   - For API Documentation go to - http://localhost:8080/swagger-ui.html 
   
##  Running with Docker

1. **Build the Docker image:**
    ```bash
   docker build -t todolist .

2. **Run the Docker container:**
    ```bash
   docker run -p 8080:8080 todolist
   
3. **Check functionality:**
    - Open a web browser and go to - http://localhost:8080
    - API Documentation - http://localhost:8080/swagger-ui.html
