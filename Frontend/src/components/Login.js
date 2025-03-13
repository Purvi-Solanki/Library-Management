import React, { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import bookCollection from "../assets/books.png";
import clock from "../assets/clock.png";
import idea from "../assets/idea.png";

const Login = () => {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [role, setRole] = useState("STUDENT");
    const navigate = useNavigate();

    useEffect(() => {
        const storedEmail = localStorage.getItem("email");
        if (storedEmail) {
            axios.get(`http://localhost:8080/api/users/getUser?email=${storedEmail}`)
                .then(response => {
                    if (response.data) {
                        if (response.data.role === "LIBRARIAN") {
                            navigate("/books");
                        } else {
                            navigate("/student-dashboard");
                        }
                    } else {
                        console.log("No user found, redirecting to login...");
                        localStorage.removeItem("email"); // Clear email if user not found
                        navigate("/");
                    }
                })
                .catch(error => {
                    console.log("User not found:", error);
                    localStorage.removeItem("email"); // Remove email if error occurs
                    navigate("/");
                });
        }
    }, [navigate]);


    const handleSubmit = async (e) => {
        e.preventDefault();
        const user = { name, email, role };

        try {
            const response = await axios.post("http://localhost:8080/api/users/register", user, {
                headers: { "Content-Type": "application/json" }
            });

            if (response.status === 200) {
                localStorage.setItem("email", response.data.email);

                if (role === "LIBRARIAN") {
                    navigate("/books");
                } else {
                    navigate("/student-dashboard");
                }
            }
        } catch (error) {
            console.error("Error registering user:", error);
            alert("Registration failed. Please try again.");
        }
    };

    return (
        <div className="login-page">
            <div className="login-container">
                <h2>Login</h2>
                <form onSubmit={handleSubmit}>
                    <input
                        type="text"
                        placeholder="Name"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                    <input
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        required
                    />
                    <select value={role} onChange={(e) => setRole(e.target.value)}>
                        <option value="STUDENT">Student</option>
                        <option value="LIBRARIAN">Librarian</option>
                    </select>
                    <button type="submit">Login</button>
                </form>
            </div>


            <div className="info-section">
                <div className="info-card">
                    <div className="info-icon">
                        <img src={bookCollection} alt="Book Collection" />
                    </div>
                    <h3>Library Collection</h3>
                    <p>Access to over 10,000 books across various genres and academic disciplines.</p>
                </div>
                <div className="info-card">
                    <div className="info-icon">
                        <img src={clock} alt="Hours" />
                    </div>
                    <h3>Extended Hours</h3>
                    <p>Open Monday to Friday: 8 AM - 8 PM<br />Saturday: 10 AM - 6 PM</p>
                </div>
                <div className="info-card">
                    <div className="info-icon">
                        <img src={idea} alt="Study Spaces" />
                    </div>
                    <h3>Study Spaces</h3>
                    <p>Quiet reading rooms, group study areas, and digital resource centers available.</p>
                </div>
            </div>

            <div className="features-section">
                <div className="feature">
                    <span className="feature-highlight">✓</span>
                    <span>Easy Book Search & Reservations</span>
                </div>
                <div className="feature">
                    <span className="feature-highlight">✓</span>
                    <span>Digital Library Card</span>
                </div>
                <div className="feature">
                    <span className="feature-highlight">✓</span>
                    <span>User Friendly website</span>
                </div>
            </div>
            <div className="credits">
                <div className="grp05"><h2>Made by Group 5 </h2> </div>
                <div className="names"><p>Nimish Somani, Aditya Solunke, Tanishka Singh, Purvi Solanki </p></div>
            </div>
        </div>



    );
};

export default Login;
