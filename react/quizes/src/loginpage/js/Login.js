import React, { Component } from 'react';
import '../css/Login.css';
import { withRouter } from 'react-router-dom'
import { getJWT } from '../../Jwt';
import cookie from 'react-cookies'
class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: ""
        }
        this.change = this.change.bind(this);
        this.submit = this.submit.bind(this);
    }
    componentDidMount() {
        var token = cookie.load('jwt')
        console.log(token);
        if (token) {
            this.props.history.push("/");
        }
    }

    componentDidUpdate(prevProps, prevState) {
        console.log("componentDidUpdate");

    }

    change(e) {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    submit(e) {
        e.preventDefault();
        console.log("submmit");
        var data = {
            "username": this.state.username,
            "password": this.state.password
        }
        var error = document.getElementById("errorMessage");
        if (data.password==="" || data.username==="") {
            error.innerHTML = "Incorrect input!";
        } else {
            this.postData(data);
        }
        
    }
    postData(data) {
        var url = 'http://localhost:8080/ServletLogin';
        fetch(url, {
            method: 'POST', // or 'PUT'
            body: JSON.stringify(data), // data can be `string` or {object}!
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(res => res.json())
            .then(response => this.processResponse(response))
            .catch(error => console.error('Error:', error));
    }

    processResponse(data) {
        var error = document.getElementById("errorMessage")
        if (data.status === 200) {
            
            var jwtToken = data.jwsToken;
            
            cookie.save('jwt', jwtToken, {
                 path: '/',
                 maxAge:1000*60*60
            })
            this.props.history.push("/");

        } else if (data.status == 400) {
            error.innerHTML = data.message;
        }
    }
    render() {
        return (
            <div>
                <div id="errorMessage"></div>
                <table id="LoginTable" cellSpacing="0">
                    <tbody>
                        <tr>
                            <td>
                                <label id="usernameLabel">Username</label>
                            </td>
                            <td>
                                <label id="passwordLabel">Password</label>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input id="usernameInput" type="text" name="username" onChange={e => this.change(e)} value={this.state.username} />
                            </td>
                            <td >
                                <input id="passwordInput" type="password" name="password" onChange={e => this.change(e)} value={this.state.password} />
                            </td>
                            <td>
                                <input id="submitInput" type="submit" value="Log in" onClick={e => this.submit(e)} />
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        );
    }
}
export default withRouter(Login);