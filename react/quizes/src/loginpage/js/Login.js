import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import '../css/Login.css';
import Register from './Register';

class Login extends Component {
    constructor() {
        super();
        console.log("LoginForm");
    }
    componentDidMount() {
        console.log("componentDidMount");
        console.log("check for users login, if user is already logged in send to Acount  page,else start login page");
    }

    componentDidUpdate(prevProps, prevState) {
        console.log("componentDidUpdate");

    }
    render() {
        return (
            <div>
                <div id="errorMessage"></div>
                <table id="LoginTable" cellSpacing="0">
                    <tbody>
                        <tr>
                            <td>
                                <label id="usernameLabel">Username or mail</label>
                            </td>
                            <td>
                                <label id="passwordLabel">Password</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input id="usernameInput" type="text" name="username" />
                            </td>
                            <td >
                                <input id="passwordInput" type="password" name="password" />
                            </td>
                            <td>
                                <input id="submitInput" type="submit" value="Log in" onClick={checkLogin} />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        );
    }
}
function checkLogin() {
    var username = document.getElementById("usernameInput").value;
    var password = document.getElementById("passwordInput").value;
    var error = document.getElementById("errorMessage");
    var data = {
        "username": username,
        "password": password
    }
    if ((username == "" || password == "")) {
        error.innerHTML = "Incorrect input!";
    } else {
        var url = 'http://localhost:8080/ProjectApi_war_exploded/ServletLogin';
        fetch(url, {
            method: 'POST', // or 'PUT'
            body: JSON.stringify(data), // data can be `string` or {object}!
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(response => processResponse(response))
            .catch(error => console.error('Error:', error));
    }
}
function processResponse(response) {
    var valid = response.valid;
    var error = document.getElementById("errorMessage");
    if (!valid) {
        error.innerHTML = "Incorrect username or password";
    } else {
        console.log("Welcome my Friend");
    }
}
export default Login;