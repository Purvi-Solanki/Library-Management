import React, { useEffect, useState } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";

const EditBook = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const [book, setBook] = useState({
    title: "",
    author: "",
    genre: "",
    available: true, // ✅ Make sure this field is considered
  });

  useEffect(() => {
    console.log("Editing book with ID:", id); // ✅ Debugging to check if `id` is valid

    if (!id) {
      setError("Invalid book ID!");
      setLoading(false);
      return;
    }

    axios
      .get(`http://localhost:8080/api/books/${id}`)
      .then((response) => {
        setBook(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error fetching book:", error);
        setError("Failed to fetch book details. Please try again!");
        setLoading(false);
      });
  }, [id]);

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setBook({
      ...book,
      [name]: type === "checkbox" ? checked : value, // ✅ Supports checkbox for "available"
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!book.title || !book.author || !book.genre) {
      alert("All fields are required! ❌");
      return;
    }

    try {
      await axios.put(`http://localhost:8080/api/books/${id}`, book);
      alert("✅ Book updated successfully!");
      navigate("/");
    } catch (error) {
      console.error("Error updating book:", error);
      alert("❌ Failed to update the book. Please check your permissions.");
    }
  };

  if (loading) {
    return <p className="text-center">Loading book details... ⏳</p>;
  }

  return (
    <div className="container fade-in">
      <div className="dashboard-header">
        <h2 className="section-title">✏️ Edit Book</h2>
      </div>
      {error && <div className="error-message">{error}</div>}
      <div className="add-book-card">
        <form onSubmit={handleSubmit}>
          <div className="book-content">
            <div className="form-group">
              <label className="book-title">Title</label>
              <input
                type="text"
                className="input-field"
                name="title"
                value={book.title}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group">
              <label className="book-title">Author</label>
              <input
                type="text"
                className="input-field"
                name="author"
                value={book.author}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group">
              <label className="book-title">Genre</label>
              <input
                type="text"
                className="input-field"
                name="genre"
                value={book.genre}
                onChange={handleChange}
                required
              />
            </div>
            <div className="form-group" style={{ display: 'flex', alignItems: 'center', gap: '10px' }}>
              <input
                type="checkbox"
                className="checkbox"
                name="available"
                checked={book.available}
                onChange={handleChange}
              />
              <label className="checkbox">Available</label>
            </div>
            <button type="submit" className="btn btn-primary">
              {loading ? "Saving..." : "Save Changes"}
            </button>
          </div>
        </form>
      </div>
    </div>
  );
};

export default EditBook;
