import React,{Component} from 'react';
import '../css/NavBar.css';
import Notifications from './notifications';
import cookie from 'react-cookies';
import { withRouter } from 'react-router-dom'

class NavBar extends Component{
    constructor() {
        super();
    }
    logout() {
        console.log(this.logout);
        cookie.remove('jwt', { path: '/' });
        this.props.history.push("/login");
    }

    search() {
        var txt = document.getElementById("searchTxt").value;
        this.props.history.push("/search/" + txt);
        console.log(txt);

    }
    render() {
        return(
            <nav className = "navBar">
                <div className = "logo">
                    <a href = "https://www.facebook.com" style = {{textDecoration:"none", color:"white"}}>Quizify</a>
                </div>
                <div className = "search">
                    <input type = "text" id = "searchTxt" placeholder = "Search for someone"
                        style={{ minWidth: "50%", width: "200%" }}></input>
                    <button className="searchBtn" onClick={() => this.search()}>Search</button>
                </div>
                <Notifications></Notifications>
                <div>
                    <button className="logOut" onClick={() => this.logout()}>Log out</button>
                </div>
            </nav>
        );
    }

}


export default withRouter(NavBar);
