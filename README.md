# Publish–Subscribe (Pub/Sub) System API

This is a simple Pub/Sub backend application built using Spring Boot.
It provides REST APIs to create topics, subscribe/unsubscribe clients, and publish messages to subscribers.

---

## 🚀 Getting Started

### 1. Generate Project with Spring Initializr
1. Go to [Spring Initializr](https://start.spring.io/).
2. Fill in the details:
   - Project: Maven
   - Language: Java
   - Spring Boot Version: (latest stable, e.g., 3.x.x)
   - Group: elevatorsystem
   - Artifact: 
   - Name: elevatorsystem
   - Packaging: Jar
   - Java: 17 (or your installed version)
3. Add dependencies:
   - Spring Web
   - - Lombok
4. Click Generate, and extract the downloaded project.

---

### 2. Open in IntelliJ IDEA
1. Open IntelliJ IDEA.
2. Import the project by selecting the extracted folder.
3. Wait until Maven builds the project and dependencies are downloaded.

---

### 3. Run the Application
1. Locate the main class:  
   src/main/java/pubsubsystem/PubsubsystemApplication.java
2. Right-click and select Run 'PubsubsystemApplication'.
3. The application will start on http://localhost:8080 by default.

---

### 4. Testing the APIs
You can use Postman or curl to test the APIs.  
For example:
- Create a Topic:  
  POST (http://localhost:8080/pubsub/topic?name=Sports)
- Subscribe to Topic:
  POST http://localhost:8080/pubsub/subscribe?topic=Sports&subscriberId=User1
- Publish Message to topic:
- http://localhost:8080/pubsub/publish
- Shutdown system:
  POST http://localhost:8080/elevators/shutdown

---

## 🛠️ Tech Stack
- Java 17+
- Spring Boot
- Maven
- REST API

---

# Pub-Sub System API Endpoints  

This project simulates a **Publisher-Subscriber (Pub-Sub) System** where:  
- Publishers can publish messages to topics  
- Subscribers can subscribe to topics  
- Subscribers can retrieve messages from subscribed topics  

---

## API Endpoints  

### 1. Create Topic  
**POST** `http://localhost:8080/topics/create?name=Sports`  
<img width="1366" height="768" alt="Screenshot (347)" src="https://github.com/user-attachments/assets/ec66351d-0949-4e71-a218-668ea47a9a95" />
- Creates a new topic in the system.  
- Requires:  
  - **name** → name of the topic.  

---

### 2. Subscribe to Topic  
**POST** `http://localhost:8080/subscribe?topic=Sports&subscriber=User1`  
<img width="1366" height="768" alt="Screenshot (348)" src="https://github.com/user-attachments/assets/2d14199f-734d-42be-8545-232f53fc87de" />

- Subscribes a user to a given topic.  
- Requires:  
  - **topic** → name of the topic.  
  - **subscriber** → unique subscriber ID.  

---

### 3. Publish Message  
**POST** `http://localhost:8080/publish?topic=Sports&message=IndiaWon`  
- Publishes a message to a topic.  
- Requires:  
  - **topic** → name of the topic.  
  - **message** → content/message to be published.   

---

### 4. Shutdown System  
**POST** `http://localhost:8080/shutdown`  
<img width="1366" height="768" alt="Screenshot (350)" src="https://github.com/user-attachments/assets/baa066d9-c340-4129-895f-5b07f0427dea" />
- Gracefully shuts down the Pub-Sub system.  
- Stops all publishing and subscriptions.  

---
