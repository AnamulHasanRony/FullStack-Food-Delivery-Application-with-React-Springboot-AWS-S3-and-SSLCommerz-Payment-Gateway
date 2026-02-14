# 🍔 Full-Stack Food Delivery Application

A modern **full-stack food ordering & delivery application** featuring:

- **React-based responsive frontend** (User + Admin Panel)
- **Secure Spring Boot backend** with JWT authentication
- **MongoDB Atlas** for cloud database
- **AWS S3** for image storage
- **SSLCommerz** payment gateway integration
- **Docker & Docker Compose** support
- Production deployments on **Netlify** (frontend) and **Render** (backend)

---

## 🌐 Live Demo

| Component     | URL                                                                      | Status |
| ------------- | ------------------------------------------------------------------------ | ------ |
| User Frontend | [Frontend](https://online-food-delivery-application-rony.netlify.app)    | Live   |
| Admin Panel   | Run locally on port 5177                                                 | Local  |
| Backend API   | [Backend](https://aws-deployable-food-delivery-app-backend.onrender.com) | Live   |

> ⚠️ Note: Backend on Render free tier may take 30–60 seconds to wake up.

---

## 📦 Docker Hub Images

Ready-to-use container images:

| Service  | Docker Hub Link                                                                                                             | Pull Command                                                      |
| -------- | --------------------------------------------------------------------------------------------------------------------------- | ----------------------------------------------------------------- |
| Backend  | [food-delivery-app-backend](https://hub.docker.com/repository/docker/mdanamulhasanrony/food-delivery-app-backend)           | `docker pull mdanamulhasanrony/food-delivery-app-backend:latest`  |
| Frontend | [food-delivery-app-frontend](https://hub.docker.com/repository/docker/mdanamulhasanrony/food-delivery-app-frontend/general) | `docker pull mdanamulhasanrony/food-delivery-app-frontend:latest` |

---

## 🛠 Tech Stack

![React](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-47A248?style=for-the-badge&logo=mongodb&logoColor=white)
![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white)

| Layer      | Technology & Tools                                       | Purpose                                 |
| ---------- | -------------------------------------------------------- | --------------------------------------- |
| Frontend   | React (Vite), JavaScript, CSS                            | Responsive UI & Admin panel             |
| Backend    | Spring Boot (Java), Spring Security, Spring Data MongoDB | REST API, JWT Authentication            |
| Database   | MongoDB Atlas                                            | Cloud NoSQL storage                     |
| Storage    | AWS S3                                                   | Food images & assets                    |
| Payment    | SSLCommerz                                               | Real payment gateway (Bangladesh)       |
| Container  | Docker, Docker Compose                                   | Local & remote image repository         |
| Deployment | Netlify, Render, AWS EC2/ECR/ECS                         | Hosting & scalable cloud infrastructure |

---

## ✨ Key Features

### Admin Panel

- Secure login (JWT)
- CRUD operations for food items (**AWS S3 image upload**)
- Order management (view list, update status: pending → processing → delivered/cancelled)

### Customer Experience

- Register & login (**JWT token-based auth**)
- Browse, search & filter food items
- Cart management
- Secure checkout with **SSLCommerz payment**
- Payment success/failure redirect → order history

### Backend & Security

- RESTful APIs with proper status codes
- Role-based access control (admin vs user)
- Input validation & error handling
- MongoDB schema design for foods, users, orders, carts

---

### 1️⃣ Clone Repository

```bash
git clone https://github.com/AnamulHasanRony/FullStack-Food-Delivery-Application-with-React-Springboot-AWS-S3-and-SSLCommerz-Payment-Gateway.git
cd FullStack-Food-Delivery-Application-with-React-Springboot-AWS-S3-and-SSLCommerz-Payment-Gateway
```

## 🚀 Local Setup (Without Docker)

### Prerequisites

- Java 21+
- Node.js 18+ & npm
- MongoDB Atlas account (or local MongoDB)
- AWS account with S3 bucket(only for admin portal)
- SSLCommerz sandbox credentials

---

### 2️⃣ Backend Setup

```bash
cd FoodDeliveryApplicationBackend

```

Fill up src/main/resources/application.properties with your credentials:

Run backend:

```bash
./mvnw spring-boot:run
# OR
mvn spring-boot:run

```

Backend API will be available at: http://localhost:8080

### 3️⃣ User Frontend Setup

```bash
cd ../foodappfrontend
npm install
```

Run user frontend:

```bash
npm run dev
```

User frontend runs at: http://localhost:5173

### 4️⃣ Admin Frontend Setup

```bash
cd ../adminpanel
npm install
```

Run admin panel:

```bash
npm run dev
```

Admin frontend runs at: http://localhost:5177

## 🐳 Run with Docker Compose (Optional)

From the **project root directory**, simply run:

```bash
docker compose up -d
```

#### ⚡ Docker Compose will automatically pull the latest images from Docker Hub and start all services:

- MongoDB → database (with volume mongo-data)

- Backend → Spring Boot API on port 8080

- User Frontend → React app on port 80

Stop all containers:

```bash
docker compose down
```

Access the apps:

Service URL

- User Frontend http://localhost:80
- Backend API http://localhost:8080

---
