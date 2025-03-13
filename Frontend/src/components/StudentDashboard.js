import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";
import '../styles/components/StudentDashboard.css'

const StudentDashboard = () => {
    const [books, setBooks] = useState([]);
    const [borrowedBooks, setBorrowedBooks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [borrowLoading, setBorrowLoading] = useState(null);
    const [returnLoading, setReturnLoading] = useState(null);
    const [error, setError] = useState(null);
    const userEmail = localStorage.getItem("email");

    // Fetch books
    const fetchBooks = async () => {
        try {
            setLoading(true);
            const response = await axios.get("http://localhost:8080/api/books");
            setBooks(response.data);
        } catch (error) {
            console.error("Error fetching books:", error);
            setError("Failed to load books. Please try again.");
        } finally {
            setLoading(false);
        }
    };
 
    // Fetch borrowed books
    const fetchBorrowedBooks = useCallback(async () => {
        try {
            if (!userEmail) return;

            const userResponse = await axios.get("http://localhost:8080/api/users/getUser", {
                params: { email: userEmail }
            });
            const userId = userResponse.data?.id;
            if (!userId) return;

            const borrowedResponse = await axios.get(`http://localhost:8080/api/transactions/user/${userId}/borrowed`);
            setBorrowedBooks(borrowedResponse.data);
        } catch (error) {
            console.error("Error fetching borrowed books:", error);
        }
    }, [userEmail]);

    useEffect(() => {
        fetchBooks();
        fetchBorrowedBooks();
    }, [fetchBorrowedBooks]);

    // Borrow book
    const borrowBook = async (bookId) => {
        try {
            if (!userEmail) {
                alert("User email not found. Please log in again.");
                return;
            }

            setBorrowLoading(bookId);

            const userResponse = await axios.get("http://localhost:8080/api/users/getUser", {
                params: { email: userEmail }
            });
            const userId = userResponse.data?.id;

            if (!userId) {
                alert("User not found. Please try again.");
                return;
            }

            await axios.post("http://localhost:8080/api/transactions/borrow", null, {
                params: { userId, bookId }
            });

            await fetchBooks();
            await fetchBorrowedBooks();
            alert("✅ Book Borrowed Successfully");
        } 
        finally {
            setBorrowLoading(null);
        }
    };

    // Return book
    const returnBook = async (transactionId) => {
        try {
            setReturnLoading(transactionId);

            await axios.post("http://localhost:8080/api/transactions/return", null, {
                params: { transactionId }
            });

            await fetchBooks();
            await fetchBorrowedBooks();
            alert("✅ Book Returned Successfully");
        } 
        finally {
            setReturnLoading(null);
        }
    };

    return (
        <div className="container fade-in">
            {error && <div className="error-message">{error}</div>}
            
            <div>
                <h2 className="section-title">Available Books</h2>
                {loading && <div className="loading">Loading books...</div>}
            </div>

            <div className="book-grid">
                {books.length > 0 ? (
                    books.map((book) => (
                        <div key={book.id} className="book-card">
                            <div className="book-content">
                                <h3 className="book-title">{book.title}</h3>
                                <p className="book-author">by {book.author}</p>
                                <p className="book-genre">{book.genre}</p>
                                <span className={`badge ${book.available ? 'badge-available' : 'badge-unavailable'}`}>
                                    {book.available ? 'Available' : 'Unavailable'}
                                    
                                </span>
                            </div>
                            <button 
                                className="btn btn-primary w-100 mt-3"
                                onClick={() => borrowBook(book.id)} 
                                disabled={borrowLoading === book.id || !book.available}
                            >
                                {borrowLoading === book.id ? (
                                    <span>Borrowing...</span>
                                ) : (
                                    <span>Borrow Book</span>
                                )}
                            </button>
                        </div>
                    ))
                ) : (
                    <div className="empty-state">
                        <p>No books available at the moment.</p>
                    </div>
                )}
            </div>

            <div className="dashboard-header mt-5">
                <h2 className="section-title">My Borrowed Books</h2>
            </div>

            <div className="book-grid">
                {borrowedBooks.length > 0 ? (
                    borrowedBooks.map((transaction) => (
                        <div key={transaction.id} className="book-card">
                            <div className="book-content">
                                <h3 className="book-title">{transaction.book.title}</h3>
                                <p className="book-author">by {transaction.book.author}</p>
                                <p className="book-genre">{transaction.book.genre}</p>
                               
                            </div>
                            <button 
                                className="btn btn-return w-100 mt-3"
                                onClick={() => returnBook(transaction.id)} 
                                disabled={returnLoading === transaction.id}
                            >
                                {returnLoading === transaction.id ? (
                                    <span>Returning...</span>
                                ) : (
                                    <span>↩ Return Book</span>
                                )}
                            </button>
                        </div>
                    ))
                ) : (
                    <div className="empty-state">
                        <p>You haven't borrowed any books yet.</p>
                    </div>
                )}
            </div>
        </div>
    );
};

export default StudentDashboard;
