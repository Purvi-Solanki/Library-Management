import React, { useEffect, useState } from "react";
import axios from "axios";
import { Link } from "react-router-dom";

const BookList = () => {
  const [books, setBooks] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchBooks = async () => {
    try {
      const response = await axios.get("http://localhost:8080/api/books");
      setBooks(response.data);
      setLoading(false);
    } catch (error) {
      console.error("Error fetching books:", error);
      setError("❌ Failed to fetch books. Please try again!");
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchBooks();
  }, []);

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this book?")) return;

    try {
      await axios.delete(`http://localhost:8080/api/books/${id}`);
      await fetchBooks(); // Refresh the list after deletion
      alert("✅ Book deleted successfully!");
    } catch (error) {
      console.error("Error deleting book:", error);
      alert("❌ Failed to delete the book.");
    }
  };

  if (loading) {
    return <div className="loading">Loading books... ⏳</div>;
  }

  return (
    <div className="container fade-in">
      <div className="dashboard-header">
        <h2 className="section-title">Library Books</h2>
        <div className="btn-add">
          <Link to="/add" style={{ display: 'block', width: '100%', height: '100%' }}>
            ➕ Add New Book
          </Link>
        </div>
        {error && <div className="error-message">{error}</div>}
      </div>

      <div className="book-grid">
        {books.length > 0 ? (
          books.map((book) => (
            <div key={book.id} className="book-card">
              <div className="book-content">
                <h3 className="book-title">{book.title}</h3>
                <p className="book-author">by {book.author}</p>
                <p className="book-genre">{book.genre}</p>
                <span className={`badge ${book.available ? 'badge-available' : 'badge-borrowed'}`}>
                  {book.available ? 'Available' : 'Borrowed'}
                </span>
              </div>
              <div className="book-actions">
                <Link 
                  to={`/edit/${book.id}`} 
                  className="btn btn-edit"
                >
                  Edit
                </Link>
                <button
                  onClick={() => handleDelete(book.id)}
                  className="btn btn-delete"
                >
                  Delete
                </button>
              </div>
            </div>
          ))
        ) : (
          <div className="empty-state">
            <p>No books available in the library. Add some books to get started! 📚</p>
          </div>
        )}
      </div>
    </div>
  );
};

export default BookList;
