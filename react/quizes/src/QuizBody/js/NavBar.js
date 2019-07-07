import React from 'react';
import '../../homepage/css/NavBar.css'
import '../../homepage/css/notifications.css'
function NavBar() {
    return (<nav className="navBar">
        <div className="logo">
            <h1 style={{ color: "white" }}>
                <a href="https://www.facebook.com" style={{ textDecoration: "none", color: "white" }}>Quizify</a>
            </h1>
        </div>
        <div className="search">
            <input type="text" name="userName" placeholder="search for someone"
                style={{ minWidth: "50%", width: "200%" }}></input>
            <button style={{ borderRadius: "10%", height: "30px" }} onClick={() => window.location.href = "https://www.facebook.com"}>search</button>
        </div>
        <table className="nots">
            <tr>
                <th><a href="#" className="notification">
                    <span>Inbox</span>
                    <span className="badge">3</span>
                </a>
                </th>
                <th><a href="#" className="notification">
                    <span>Challenges</span>
                    <span className="badge">3</span>
                </a>
                </th>
                <th><a href="#" className="notification">
                    <span>Requests</span>
                    <span className="badge">3</span>
                </a>
                </th>
            </tr>
        </table>
        <div>
            <a href="https://www.facebook.com"><button className="logOut">log out</button></a>
        </div>
    </nav>
    )
}

export default NavBar