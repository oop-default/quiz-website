import React,{Component} from 'react';
import '../css/NavBar.css';
import Notifications from './Notifications';


class NavBar extends Component{
    // http://localhost:3000/createQuiz
    componentDidMount() {
      document.getElementById("create").onclick = function () {
        window.location.href = "http://localhost:3000/createQuiz";
      };
    }

    render() {
        return(
            <nav className = "navBar">
                <div className = "logo">
                    <a href = "http://localhost:3000/" style = {{textDecoration:"none", color:"white"}}>Quizify</a>
                </div>
                <button id="create" className="createNewQuiz">Create New Quiz</button>
                <div className = "search">
                    <input type = "text" name = "userName" placeholder = "Search for someone"
                        style={{ minWidth: "50%", width: "200%" }}></input>
                    <button className="searchBtn" onClick={() => window.location.href = "/search/1"}>Search</button>
                </div>
                <Notifications></Notifications>
                <div>
                    <a href = "/login"><button className="logOut">Log out</button></a>
                </div>
            </nav>
        );
    }

}


export default NavBar;
