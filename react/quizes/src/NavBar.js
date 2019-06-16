import React,{Component} from 'react';
import './NavBar.css';
import Notifications from './notifications';


class NavBar extends Component{

    constructor() {
        super();
    }


    render() {
        return(
            <nav className = "navBar">
                <div className = "logo">
                    <a href = "https://www.facebook.com" style = {{textDecoration:"none", color:"white"}}>Quizify</a>
                </div>
                <div className = "search">
                    <input type = "text" name = "userName" placeholder = "search for someone"
                        style={{minWidth:"50%", width:"200%"}}></input>
                    <button style={{borderRadius:"10%", height:"30px", cursor:"pointer"}}onClick={()=> window.location.href = "https://www.facebook.com"}>search</button>
                </div>
                <Notifications></Notifications>
                <div>
                    <a href = "https://www.facebook.com"><button className="logOut">log out</button></a>
                </div>
            </nav>
        );
    }

}


export default NavBar;
