# Library Management System

## 📌 Overview
This is a **Library Management System** built using **Java and Object-Oriented Programming (OOP) principles**. It allows users to manage books, members, and borrowing records with a simple **Graphical User Interface (GUI)**.

## ⚙️ Features
- 📚 **Book Management**: Add, remove, and search books by title, author, or ISBN.
- 👥 **Member Management**: Register members and track borrowed books.
- 🔄 **Borrow & Return Books**: Maintain records of books issued and returned.
- 🔍 **Search & Filter**: Search books and members with various filters.
- 🖥 **Simple UI**: Interactive user interface for easy management.

## 🏗 Technologies Used
- **Java** (OOP concepts: Encapsulation, Inheritance, Polymorphism, Abstraction)
- **Swing (GUI) or JavaFX** (for user interface)
- **Collections Framework** (ArrayList, HashMap for data storage)
- **VS Code or IntelliJ IDEA** (Recommended IDE)

---

## 🚀 How to Run the Project

### **1️⃣ Setup the Project**
1. Clone this repository or download the source code.
   ```sh
   git clone https://github.com/your-repo/library-management-system.git
   cd library-management-system
   ```
2. Open the project in **VS Code** or **IntelliJ IDEA**.

### **2️⃣ Compile & Run Using VS Code**
1. Open the **Terminal** (`Ctrl + ~`).
2. Compile the project:
   ```sh
   javac -d out src/main/Main.java
   ```
3. Run the project:
   ```sh
   java -cp out main.Main
   ```

### **3️⃣ Compile & Run Using Command Line**
1. Navigate to the project folder:
   ```sh
   cd "C:\Users\YourUser\Desktop\LibraryManagementSystem"
   ```
2. Compile all Java files:
   ```sh
   javac -d out src/main/Main.java
   ```
3. Run the application:
   ```sh
   java -cp out main.Main
   ```

---

## 📂 Project Structure
```
LibraryManagementSystem/
│── src/
│   ├── main/             # Contains Main.java (Entry Point)
│   │   ├── Main.java
│   ├── ui/               # Contains UI components
│   │   ├── LibraryUI.java
│   ├── models/           # Data models (Book, Member, etc.)
│   │   ├── Book.java
│   │   ├── Member.java
│   ├── services/         # Business logic
│   │   ├── Library.java
│── out/                  # Compiled .class files (Generated after compilation)
│── README.md
```

---

## 🛠 Troubleshooting
**1️⃣ Package Not Found Error**
- Ensure `package ui;` is at the top of `LibraryUI.java`.
- Move `LibraryUI.java` to `src/ui/` if necessary.

**2️⃣ Cannot Find Class Error**
- Refresh the project (`Right-click on src → Refresh`).
- Recompile all Java files manually:
  ```sh
  javac -d out src/ui/LibraryUI.java src/main/Main.java
  java -cp out main.Main
  ```

---

## 📜 License
This project is open-source and free to use under the **MIT License**.

---

## 🙌 Contributors
- [Purvi Solanki](https://github.com/Purvi-Solanki)
- [Other Contributors]

Feel free to contribute and enhance this project! 🚀

