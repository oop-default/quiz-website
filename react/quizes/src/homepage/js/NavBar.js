import React,{Component} from 'react';
import '../css/NavBar.css';
import Notifications from './Notifications';


class NavBar extends Component{
    render() {
        return(
            <nav className = "navBar">
                <div className = "logo">
                    <a href = "" style = {{textDecoration:"none", color:"white"}}>Quizify</a>
                </div>
                <div className = "search">
                    <input type = "text" name = "userName" placeholder = "Search for someone"
                        style={{ minWidth: "50%", width: "200%" }}></input>
                    <button className="searchBtn" onClick={() => window.location.href = "https://www.facebook.com"}>Search</button>
                </div>
                <Notifications></Notifications>
                <div>
                    <a href = "https://www.facebook.com"><button className="logOut">Log out</button></a>
                </div>
            </nav>
        );
    }

}


export default NavBar;
