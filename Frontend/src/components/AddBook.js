import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import '../styles/components/StudentDashboard.css'; // Reuse the existing styles

const AddBook = () => {
  const [book, setBook] = useState({
    title: "",
    author: "",
    genre: "",
    available: true,
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);
  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setBook({
      ...book,
      [name]: type === "checkbox" ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError(null);

    // Validate inputs
    if (!book.title.trim() || !book.author.trim() || !book.genre.trim()) {
      setError("Please fill in all fields");
      setLoading(false);
      return;
    }

    try {
      const response = await axios.post("http://localhost:8080/api/books", {
        title: book.title.trim(),
        author: book.author.trim(),
        genre: book.genre.trim(),
        available: book.available
      });

      if (response.data) {
        alert("✅ Book added successfully!");
        navigate("/");
      }
    } catch (error) {
      console.error("Error adding book:", error);
      setError(error.response?.data?.message || "Failed to add book. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container fade-in">
      <div className="dashboard-header">
        <h2 className="section-title">Add New Book</h2>
      </div>

      {error && <div className="error-message">{error}</div>}
      <div className="">
        <div className="add-book-card">
          <form onSubmit={handleSubmit}>
            <div className="book-content">
              <div className="form-group">
                <label className="book-title">
                  Title
                </label>
                <input
                  type="text"
                  name="title"
                  value={book.title}
                  onChange={handleChange}
                  className="input-field"
                  required
                />
              </div>

              <div className="form-group">
                <label className="book-title">
                  Author
                </label>
                <input
                  type="text"
                  name="author"
                  value={book.author}
                  onChange={handleChange}
                  className="input-field"
                  required
                />
              </div>

              <div className="form-group">
                <label className="book-title">
                  Genre
                </label>
                <input
                  type="text"
                  name="genre"
                  value={book.genre}
                  onChange={handleChange}
                  className="input-field"
                  required
                />
              </div>

              <div className="form-group" style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
                <input
                  type="checkbox"
                  name="available"
                  checked={book.available}
                  onChange={handleChange}
                  className="checkbox"
                />
                <label className="book-author">
                  Available for borrowing
                </label>
              </div>

              <button
                type="submit"
                className="btn btn-primary"
                disabled={loading}
              >
                {loading ? "Adding Book..." : "Add Book"}
              </button>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
};

export default AddBook;
