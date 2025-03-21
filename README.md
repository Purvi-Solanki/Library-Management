# Library Management System

A modern, full-stack Library Management System that allows librarians to manage books and students to borrow/return books. Built with Spring Boot and React.js.

## 📸UI INTERFACE
Login Interface
![image](https://github.com/user-attachments/assets/8ee687a2-47c0-4153-bdfe-fee15401014d)
Student Dashboard
![image](https://github.com/user-attachments/assets/232ae40b-727a-4233-b3fd-19d4f3887f31)
Librarian Interface
![image](https://github.com/user-attachments/assets/3c3baa43-f91b-4280-88bc-4b0f8d486309)



## 🚀 Features

### For Librarians
- Manage book inventory (Add, Edit, Delete books)
- View all books in the library
- Track book availability status
- Soft delete functionality for books
- Complete book management dashboard

### For Students
- Browse available books
- Borrow and return books
- View borrowed books history
- User-friendly dashboard interface

### General Features
- Role-based authentication (Student/Librarian)
- Responsive design
- Real-time availability updates
- User-friendly interface
- Secure API endpoints

## 🛠️ Tech Stack

### Backend
- Java 17
- Spring Boot
- Spring Data JPA
- MySQL Database
- RESTful API architecture

### Frontend
- React.js
- React Router for navigation
- Axios for API calls
- CSS3 with custom styling
- Responsive design

## 📋 Prerequisites

Before running this project, make sure you have:
- Java Development Kit (JDK) 17 or higher
- Node.js and npm
- MySQL Server
- Maven

## 🔧 Installation & Setup

### Backend Setup

1. Clone the repository:
```bash
git clone https://github.com/purvi-solanki/library-management.git
```

2. Navigate to the backend directory:
```bash
cd Library-Management/Backend
```

3. Configure MySQL database in `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. Run the Spring Boot application:
```bash
mvn spring-boot:run
```

### Frontend Setup

1. Navigate to the frontend directory:
```bash
cd Library-Management/Frontend
```

2. Install dependencies:
```bash
npm install
```

3. Start the React development server:
```bash
npm start
```

## 🌐 Usage

1. Access the application at `http://localhost:3000`
2. Login as either a Student or Librarian
3. For Librarians:
   - Add new books using the "Add New Book" button
   - Edit or delete existing books
   - Manage book inventory
4. For Students:
   - Browse available books
   - Borrow books using the "Borrow" button
   - Return books from the dashboard


## 🎨 UI Components

- Modern and intuitive user interface
- Responsive design for all screen sizes
- Custom styling with CSS3
- Interactive elements with hover effects
- Clean and professional color scheme

## 👥 Contributors

- [Nimish Somani](https://github.com/Nimish1106)
- [Purvi Solanki](https://github.com/Purvi-Solanki)
- Aditya Solunke
- [Tanishka Singh](https://github.com/Tanishka-Singh05)


## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the project
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📞 Support

For support, email @nimishsomani1106@gmail.com
