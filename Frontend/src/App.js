import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";
import BookList from "./components/BookList";
import EditBook from "./components/EditBook";
import AddBook from "./components/AddBook";
import Login from "./components/Login";
import StudentDashboard from "./components/StudentDashboard";
import "./styles/index.css"; // Import App.css

function App() {
  return (
    <Router>
      {/* Navbar should be outside Routes */}
      <Navbar />

      <Routes>
        {/* Login Page */}
        <Route path="/" element={<Login />} />

        {/* Librarian Dashboard (BookList) */}
        <Route path="/books" element={<BookList />} />

        {/* Add Book Route */}
        <Route path="/add" element={<AddBook />} />

        <Route path="/edit/:id" element={<EditBook />} />

        {/* Student Dashboard */}
        <Route path="/student-dashboard" element={<StudentDashboard />} />
      </Routes>
    </Router>
  );
}

export default App;
