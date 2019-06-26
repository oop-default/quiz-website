import React, { Component } from 'react';
import Register from './Register';
import Login from './Login';
import '../css/Loginpage.css';

class Loginpage extends Component {
    constructor() {
        super();
        console.log("Homepage");
    }
    componentDidMount() {

        console.log("componentDidMount");
        console.log("check for users login, if user is already logged in send to Acount  page,else start login page");
    }

    componentDidUpdate(prevProps, prevState) {
        console.log("componentDidUpdate");

    }
    render() {
        return (<div>
            <ul>
                <li id="siteName">QuizSite</li>
                <li id="LoginTable">
                    <Login />
                </li>
            </ul>
            <div id="register"><Register /></div>
        </div>

        );
    }
}

export default Loginpage;